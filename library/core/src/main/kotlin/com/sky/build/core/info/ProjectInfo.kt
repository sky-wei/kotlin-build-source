package com.sky.build.core.info

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by sky on 2021/10/10.
 */
data class ProjectInfo(
    val alias: String,
    val packageName: String,
    val author: String,
    val time: String,
    val outRootDir: String
) {

    companion object {

        private val mSimpleDateFormat: SimpleDateFormat by lazy {
            SimpleDateFormat("yyyy-MM-dd")
        }

        fun valueOf(
            alias: String, packageName: String,
            author: String = "sky",
            time: String = mSimpleDateFormat.format(Date()),
            outRootDir: String = "out/"
        ): ProjectInfo {
            return ProjectInfo(alias, packageName, author, time, outRootDir)
        }
    }
}