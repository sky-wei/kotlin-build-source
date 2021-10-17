package com.sky.build.source.generate.android

import com.sky.build.annotations.AGenerate
import com.sky.build.core.interfaces.XGenerateManager
import com.sky.build.source.base.BaseGenerate

/**
 * Created by sky on 2021/10/17.
 */
@AGenerate(name = "Room")
class RoomGenerate(
    generateManager: XGenerateManager
) : BaseGenerate(generateManager) {

    override val templateDir: String
        get() = "src-template/android/room"

    override fun onProcess() {

        val dataModel = hashMapOf(
            "project" to project
        )

        process(
            getTemplate("TemplateDatabase.kt.ftl"),
            dataModel,
            newOutWriter(
                appendPackageName("data.disk"),
                "${project.alias}Database.kt"
            )
        )

        process(
            getTemplate("TemplateDao.kt.ftl"),
            dataModel,
            newOutWriter(
                appendPackageName("data.disk.dao"),
                "TemplateDao.kt"
            )
        )

        process(
            getTemplate("TemplateEntity.kt.ftl"),
            dataModel,
            newOutWriter(
                appendPackageName("data.disk.entity"),
                "TemplateEntity.kt"
            )
        )
    }
}