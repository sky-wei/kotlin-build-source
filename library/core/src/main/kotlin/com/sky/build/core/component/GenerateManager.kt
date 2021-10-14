package com.sky.build.core.component

import com.sky.build.core.info.AGenerateInfo
import com.sky.build.core.info.ProjectInfo
import com.sky.build.core.interfaces.XGenerate
import com.sky.build.core.interfaces.XGenerateManager
import java.lang.reflect.Constructor


/**
 * Created by sky on 2021-10-14.
 */
class GenerateManager private constructor(
    builder: Builder
) : XGenerateManager {

    private val mProject: ProjectInfo = builder.project
    private val mLoader: XGenerateManager.Loader = builder.loader!!
    private val mFilter: XGenerateManager.Filter = builder.filter
    private val mFactory: XGenerateManager.Factory = builder.factory

    override val project: ProjectInfo
        get() = mProject

    override fun process() {
        loadGenerateInfo().forEach { onProcess(it) }
    }

    private fun onProcess(info: AGenerateInfo) {

        var xGenerate: XGenerate? = null

        try {
            xGenerate = mFactory.create(this, info)
            xGenerate.initialize()
            xGenerate.process()
        } catch (tr: Throwable) {
            tr.printStackTrace()
        } finally {
            xGenerate?.release()
        }
    }

    /**
     * 加载
     * @return
     */
    private fun loadGenerateInfo(): List<AGenerateInfo> {
        return mLoader.loadGenerateInfo().filter { mFilter.accept(it) }
    }

    /**
     * 内置过滤类
     */
    private class InternalGenerateFilter : XGenerateManager.Filter {
        override fun accept(info: AGenerateInfo): Boolean {
            return !info.debug
        }
    }

    /**
     * 内置工厂类
     */
    private class InternalGenerateFactory : XGenerateManager.Factory {

        override fun create(generateManager: XGenerateManager, info: AGenerateInfo): XGenerate {
            try {
                val constructor: Constructor<out XGenerate> = info
                    .generateClass.getConstructor(XGenerateManager::class.java)
                // 创建XGenerate对象
                return constructor.newInstance(generateManager)
            } catch (tr: Throwable) {
                tr.printStackTrace()
                throw tr
            }
        }
    }

    class Builder {

        var project: ProjectInfo = ProjectInfo.valueOf("Demo", "com.sky.demo")
        var loader: XGenerateManager.Loader? = null
        var factory: XGenerateManager.Factory = InternalGenerateFactory()
        var filter: XGenerateManager.Filter = InternalGenerateFilter()

        fun build(): XGenerateManager {

            loader?: throw IllegalArgumentException("参数不能为空")

            return GenerateManager(this)
        }
    }

    companion object {

        fun build(init: Builder.() -> Unit): XGenerateManager {
            return Builder().apply(init).build()
        }
    }
}