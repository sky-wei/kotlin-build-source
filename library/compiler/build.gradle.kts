plugins {
    kotlin("jvm")
    kotlin("kapt")
}

group = "com.sky.build.source"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.google.auto.service:auto-service:1.0-rc5")
    kapt("com.google.auto.service:auto-service:1.0-rc5")
    implementation("com.squareup:javapoet:1.10.0")
    implementation("com.google.auto:auto-common:0.10")
    implementation("com.google.code.gson:gson:2.8.6")
    implementation(project(":annotations"))
}

tasks.test {
    useJUnit()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}