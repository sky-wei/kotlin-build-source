package com.sky.build.compiler

import com.sky.build.compiler.generate.GenerateStore
import com.sky.build.compiler.info.AGenerateInfo
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.TypeSpec
import java.io.IOException
import javax.annotation.processing.Filer
import javax.annotation.processing.Messager
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.Element
import javax.lang.model.util.Elements
import javax.tools.Diagnostic

/**
 * Created by sky on 2021-06-08.
 */
internal class GenerateSourceFiles(processing: ProcessingEnvironment) {

    private val mProcessing: ProcessingEnvironment = processing
    private val mFiler: Filer = mProcessing.filer
    private val mMessager: Messager = mProcessing.messager
    private val mElements: Elements = mProcessing.elementUtils

    fun generate(aGenerateInfos: List<AGenerateInfo>) {

        val packageName = "com.sky.build.source"

        // 生成相应的类
        val storeClassName: ClassName = ClassName.get(
            packageName,
            "GenerateStore"
        )

        val generateStore = GenerateStore(mProcessing)
        generateFile(packageName, generateStore.generate(storeClassName, aGenerateInfos))
    }

    private fun generateFile(packageName: String, typeSpec: TypeSpec) {
        try {
            JavaFile.builder(packageName, typeSpec)
                .build()
                .writeTo(mFiler)
        } catch (e: IOException) {
            printError("生成文件异常!")
        }
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