package com.sky.build.source

import com.sky.build.source.data.model.ProjectModel
import freemarker.template.Configuration
import java.io.File
import java.io.FileWriter

/**
 * Created by sky on 2021/10/10.
 */
fun main() {

    val projectModel = ProjectModel.valueOf("Milk", "com.sky.test")

    println(">>>>>>>>>>>>> $projectModel")

    // 第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对于的版本号。
    val configuration = Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS)
    // 第二步：设置模板文件所在的路径。
    configuration.setDirectoryForTemplateLoading(File("src-template"))
    // 第三步：设置模板文件使用的字符集。一般就是utf-8.
    configuration.defaultEncoding = "UTF-8"
    // 第四步：加载一个模板，创建一个模板对象。
    val template = configuration.getTemplate("XResult.kt.ftl")
    // 第五步：创建一个模板使用的数据集，可以是pojo也可以是map。一般是Map。
    val dataModel = HashMap<String, Any>()
    //向数据集中添加数据
    dataModel["project"] = projectModel
    // 第六步：创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
    val out = FileWriter(File("XResult.kt"))
    // 第七步：调用模板对象的process方法输出文件。
    template.process(dataModel, out);
    // 第八步：关闭流。
    out.close();
}