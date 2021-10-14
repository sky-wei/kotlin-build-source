package com.sky.build.compiler

import com.google.auto.service.AutoService
import com.sky.build.annotations.AGenerate
import com.sky.build.compiler.handler.GenerateAnnotationHandler
import com.sky.build.compiler.info.AGenerateInfo
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.tools.Diagnostic


/**
 * Created by sky on 2021/10/13.
 */
@AutoService(Processor::class)
class XAnnotationProcessor : AbstractProcessor() {

    private lateinit var mMessager: Messager
    private lateinit var mElements: Elements
    private lateinit var mFiler: Filer

    private var mAGenerateList: ArrayList<AGenerateInfo> = ArrayList()

    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)
        mMessager = processingEnv.messager
        mElements = processingEnv.elementUtils
        mFiler = processingEnv.filer
    }

    override fun process(
        annotations: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment
    ): Boolean {

        if (annotations.isNullOrEmpty()) {
            return false
        }

        // 开始处理注解
        annotationProcessor(roundEnv)

        // 生成相应的文件
        generateConfigFiles()
        return true
    }

    private fun generateConfigFiles() {
        GenerateSourceFiles(processingEnv).generate(mAGenerateList)
        printInfo("注解处理器处理完成...")
    }

    private fun annotationProcessor(roundEnvironment: RoundEnvironment) {
        printInfo("注解处理器开始运行...")
        try {
            mAGenerateList.addAll(
                GenerateAnnotationHandler(processingEnv).handler(roundEnvironment)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            printError("处理异常...")
        }
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(
            AGenerate::class.java.canonicalName
        )
    }

    fun printError(e: Element, msg: String, vararg args: Any) {
        mMessager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, *args), e)
    }

    fun printError(msg: String, vararg args: Any) {
        mMessager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, *args))
    }

    fun printInfo(msg: String, vararg args: Any) {
        mMessager.printMessage(Diagnostic.Kind.NOTE, String.format(msg, *args))
    }
}