package com.sky.build.source.base

import com.sky.build.core.abstracts.AbstractGenerate
import com.sky.build.core.info.ProjectInfo
import com.sky.build.core.interfaces.XGenerateManager
import freemarker.template.Configuration
import freemarker.template.Template
import java.io.File
import java.io.FileWriter
import java.io.Writer

/**
 * Created by sky on 2021-10-14.
 */
abstract class BaseGenerate(
    generateManager: XGenerateManager
) : AbstractGenerate(generateManager) {

    open val project: ProjectInfo
        get() = generateManager.project

    lateinit var configuration: Configuration

    abstract val templateDir: String

    override fun initialize() {
        super.initialize()
        configuration = createConfiguration(templateDir)
    }

    open fun createConfiguration(templateDir: String): Configuration {
        return Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS).apply {
            defaultEncoding = "UTF-8"
            setDirectoryForTemplateLoading(File(templateDir))
        }
    }

    fun getTemplate(name: String): Template {
        return configuration.getTemplate(name)
    }

    fun newOutWriter(packageName: String, fileName: String): FileWriter {
        val dir = File(project.outRootDir, packageName.replace(".", "/"))
        if (!dir.exists()) dir.mkdirs()
        return FileWriter(File(dir, fileName))
    }

    fun process(template: Template, dataModel: Any, writer: Writer) {
        try {
            template.process(dataModel, writer)
        } catch (tr: Throwable) {
            tr.printStackTrace()
        } finally {
            writer.close()
        }
    }
}