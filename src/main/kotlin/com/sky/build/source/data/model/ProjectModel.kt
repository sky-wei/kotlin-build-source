package com.sky.build.source.data.model

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by sky on 2021/10/10.
 */
data class ProjectModel(
    val alias: String,
    val packageName: String,
    val author: String,
    val time: String
) {

    companion object {

        private val mSimpleDateFormat: SimpleDateFormat by lazy {
            SimpleDateFormat("yyyy-MM-dd")
        }

        fun valueOf(
            alias: String, packageName: String, author: String = "sky"
        ): ProjectModel {
            val time = mSimpleDateFormat.format(Date())
            return ProjectModel(alias, packageName, author, time)
        }
    }
}