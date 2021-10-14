package com.sky.build.source.generate

import com.sky.build.annotations.AGenerate
import com.sky.build.core.interfaces.XGenerateManager
import com.sky.build.source.base.BaseGenerate

/**
 * Created by sky on 2021/10/12.
 */
@AGenerate
class XResultGenerate(
    generateManager: XGenerateManager
) : BaseGenerate(generateManager) {

    override val templateDir: String
        get() = "src-template"

    override fun onProcess() {

        val dataModel = hashMapOf(
            "project" to project
        )

        process(
            getTemplate("XResult.kt.ftl"),
            dataModel,
            newOutWriter(project.packageName, "XResult.kt")
        )
    }
}