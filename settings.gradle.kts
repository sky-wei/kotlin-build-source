pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

rootProject.name = "kotlin-build-source"

file("library").listFiles()?.forEach {
    include(":${it.name}")
    project(":${it.name}").projectDir = it
}
