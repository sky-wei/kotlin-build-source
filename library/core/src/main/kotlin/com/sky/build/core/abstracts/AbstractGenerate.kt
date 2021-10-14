package com.sky.build.core.abstracts

import com.sky.build.core.interfaces.XGenerate
import com.sky.build.core.interfaces.XGenerateManager

/**
 * Created by sky on 2021/10/12.
 */
abstract class AbstractGenerate(
    val generateManager: XGenerateManager
) : XGenerate {

    override fun initialize() {
    }

    override fun process() {
        try {
            onProcess()
        } catch (tr: Throwable) {
            tr.printStackTrace()
        }
    }

    @Throws(Exception::class)
    abstract fun onProcess()

    override fun release() {
    }
}