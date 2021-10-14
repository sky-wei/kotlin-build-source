package com.sky.build.compiler

import com.google.auto.service.AutoService
import com.sky.build.annotations.AGenerate
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.TypeElement

/**
 * Created by sky on 2021/10/13.
 */
@AutoService(Processor::class)
class XAnnotationProcessor : AbstractProcessor() {

    override fun process(
        annotations: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(
            AGenerate::class.java.canonicalName
        )
    }
}