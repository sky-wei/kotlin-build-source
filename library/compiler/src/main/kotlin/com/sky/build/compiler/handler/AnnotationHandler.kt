package com.sky.build.compiler.handler

import javax.annotation.processing.Messager
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.Element
import javax.tools.Diagnostic

/**
 * Created by sky on 2020-01-17.
 */
abstract class AnnotationHandler<T>(
    private val mProcessing: ProcessingEnvironment
) {

    private val mMessager: Messager = mProcessing.messager

    abstract fun handler(environment: RoundEnvironment): T

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