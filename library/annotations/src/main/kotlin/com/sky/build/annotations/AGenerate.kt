package com.sky.build.annotations

/**
 * Created by sky on 2021/10/13.
 */
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class AGenerate(
    val name: String = "",
    val debug: Boolean = false,
    val desc: String = ""
)
