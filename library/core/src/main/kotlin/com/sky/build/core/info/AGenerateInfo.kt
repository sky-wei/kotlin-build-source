package com.sky.build.core.info

import com.sky.build.core.interfaces.XGenerate

/**
 * Created by sky on 2021-10-14.
 */
data class AGenerateInfo(
    val name: String,
    val debug: Boolean,
    val desc: String,
    val generateClass: Class<out XGenerate>
)