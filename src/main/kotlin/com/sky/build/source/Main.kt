package com.sky.build.source

import com.sky.build.core.component.GenerateManager
import com.sky.build.core.info.AGenerateInfo
import com.sky.build.core.info.ProjectInfo
import com.sky.build.core.interfaces.XGenerateManager

/**
 * Created by sky on 2021/10/10.
 */
fun main() {

    GenerateManager.build {
        project = ProjectInfo.valueOf("Milk", "com.sky.test")
        loader =  object: XGenerateManager.Loader {
            override fun loadGenerateInfo(): List<AGenerateInfo> {
                return GenerateStore.getGenerateInfo()
            }
        }
    }.process()
}