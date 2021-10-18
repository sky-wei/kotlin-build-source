package com.sky.build.compiler.generate

import com.sky.build.compiler.info.AGenerateInfo
import com.squareup.javapoet.*
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.Modifier

/**
 * Created by sky on 2021-06-08.
 */
class GenerateStore(
    processing: ProcessingEnvironment
) : BaseGenerate(processing) {

    fun generate(className: ClassName, aGenerateInfos: List<AGenerateInfo>): TypeSpec {
        val builderStore: TypeSpec.Builder = TypeSpec.classBuilder(className)
        builderStore.addMethod(generateGetInfo(aGenerateInfos))
        builderStore.addModifiers(Modifier.PUBLIC)
        return builderStore.build()
    }

    private fun generateGetInfo(aGenerateInfos: List<AGenerateInfo>): MethodSpec {
        val generateInfoClassName: ClassName =
            className("com.sky.build.core.info.AGenerateInfo")
        val builder: CodeBlock.Builder = CodeBlock.builder()
            .addStatement(
                "\$T<\$T> list = new \$T<>()",
                List::class.java,
                generateInfoClassName,
                ArrayList::class.java
            )
        for (info in aGenerateInfos) {
            builder.addStatement(
                "list.add(new \$T(\$S, \$L, \$S, \$S, \$T.class))",
                generateInfoClassName,
                info.name, info.debug, info.desc, info.group,
                className(info.generateClass)
            )
        }
        builder.addStatement("return list").build()
        return MethodSpec.methodBuilder("getGenerateInfo")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .addCode(builder.build())
            .returns(TypeVariableName.get("List<AGenerateInfo>"))
            .build()
    }
}