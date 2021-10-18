package com.sky.build.source.generate.android

import com.sky.build.annotations.AGenerate
import com.sky.build.core.interfaces.XGenerateManager
import com.sky.build.source.XConstant
import com.sky.build.source.base.BaseGenerate

/**
 * Created by sky on 2021/10/17.
 */
@AGenerate(name = "Net", group = XConstant.Group.ANDROID)
class NetGenerate(
    generateManager: XGenerateManager
) : BaseGenerate(generateManager) {

    override val templateDir: String
        get() = "src-template/android/net"

    override fun onProcess() {

        val dataModel = hashMapOf(
            "project" to project
        )

        process(
            getTemplate("XResult.kt.ftl"),
            dataModel,
            newOutWriter(
                appendPackageName("data.model"),
                "XResult.kt"
            )
        )

        process(
            getTemplate("XResultEx.kt.ftl"),
            dataModel,
            newOutWriter(
                appendPackageName("ex"),
                "XResultEx.kt"
            )
        )

        process(
            getTemplate("DataException.kt.ftl"),
            dataModel,
            newOutWriter(
                appendPackageName("data"),
                "DataException.kt"
            )
        )

        process(
            getTemplate("ResponseException.kt.ftl"),
            dataModel,
            newOutWriter(
                appendPackageName("data"),
                "ResponseException.kt"
            )
        )
    }
}