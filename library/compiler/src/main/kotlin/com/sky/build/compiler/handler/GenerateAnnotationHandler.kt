package com.sky.build.compiler.handler

import com.sky.build.annotations.AGenerate
import com.sky.build.compiler.info.AGenerateInfo
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement


/**
 * Created by sky on 2021-10-14.
 */
class GenerateAnnotationHandler(
    processing: ProcessingEnvironment
) : AnnotationHandler<List<AGenerateInfo>>(processing) {

    override fun handler(environment: RoundEnvironment): List<AGenerateInfo> {

        val aGenerateInfos = ArrayList<AGenerateInfo>()

        aGenerateInfos.addAll(
            handlerGenerateInfo(environment.getElementsAnnotatedWith(AGenerate::class.java))
        )

        return aGenerateInfos
    }

    private fun handlerGenerateInfo(elements: Set<Element>): List<AGenerateInfo> {

        if (elements.isEmpty()) {
            return arrayListOf()
        }

        val iterator: Iterator<*> = elements.iterator()
        val aGenerateInfos = ArrayList<AGenerateInfo>()

        while (iterator.hasNext()) {

            val element = iterator.next() as TypeElement

            if (!element.kind.isClass) continue

            val className = element.qualifiedName.toString()
            val aGenerate: AGenerate = element.getAnnotation(AGenerate::class.java)

            aGenerateInfos.add(
                handlerGenerateInfo(aGenerate, className)
            )
        }
        return aGenerateInfos
    }

    private fun handlerGenerateInfo(aGenerate: AGenerate, className: String): AGenerateInfo {
        return AGenerateInfo(
            aGenerate.name,
            aGenerate.debug,
            aGenerate.desc,
            aGenerate.group,
            className
        )
    }
}