package com.sky.build.core.interfaces

import com.sky.build.core.info.AGenerateInfo
import com.sky.build.core.info.ProjectInfo

/**
 * Created by sky on 2021-10-14.
 */
interface XGenerateManager {

    /**
     * 项目信息
     */
    val project: ProjectInfo

    /**
     * 处理
     */
    fun process()


    /**
     * 过滤接口
     */
    interface Filter {

        fun accept(info: AGenerateInfo): Boolean
    }

    /**
     * 工厂接口
     */
    interface Factory {

        /**
         * 创建
         */
        fun create(generateManager: XGenerateManager, info: AGenerateInfo): XGenerate
    }

    /**
     * 加载接口
     */
    interface Loader {

        /**
         * 加载列表
         */
        fun loadGenerateInfo(): List<AGenerateInfo>
    }
}