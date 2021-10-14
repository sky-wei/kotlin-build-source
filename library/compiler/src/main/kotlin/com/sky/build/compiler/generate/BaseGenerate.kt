package com.sky.build.compiler.generate

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.TypeSpec
import java.io.IOException
import javax.annotation.processing.Messager
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.tools.Diagnostic

/**
 * Created by sky on 2020-02-16.
 */
abstract class BaseGenerate(var mProcessing: ProcessingEnvironment) {

    var mMessager: Messager = mProcessing.messager
    var mElements: Elements = mProcessing.elementUtils

    fun printError(e: Element, msg: String, vararg args: Any) {
        mMessager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, *args), e)
    }

    fun printError(msg: String, vararg args: Any) {
        mMessager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, *args))
    }

    fun printInfo(msg: String, vararg args: Any) {
        mMessager.printMessage(Diagnostic.Kind.NOTE, String.format(msg, *args))
    }

    fun className(className: String): ClassName {
        return ClassName.get(findTypeElement(className))
    }

    fun findTypeElement(className: String): TypeElement {
        return mElements.getTypeElement(className)
    }

    fun generateFile(packageName: String, typeSpec: TypeSpec) {
        try {
            JavaFile.builder(packageName, typeSpec)
                .build()
                .writeTo(mProcessing.filer)
        } catch (e: IOException) {
            printError("生成文件异常!")
        }
    }
}