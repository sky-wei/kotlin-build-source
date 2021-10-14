pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

rootProject.name = "kotlin-build-source"

//include("library:annotations")

include(":annotations")
project(":annotations").projectDir = File("library/annotations")

include(":compiler")
project(":compiler").projectDir = File("library/compiler")
