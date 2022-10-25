import java.util.*

plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

val props = Properties().apply {
    file("../gradle.properties").inputStream().use { load(it) }
}

dependencies {
    val kotlinVersion = props.getProperty("kotlin_version")
    implementation(kotlin("gradle-plugin", kotlinVersion))
    implementation(gradleApi())
}

gradlePlugin {
    plugins {
        register("multiplatform-library"){
            id = "multiplatform-library"
            implementationClass = "es.test.buildSrc.MultiplatformLibrary"
        }
    }
}