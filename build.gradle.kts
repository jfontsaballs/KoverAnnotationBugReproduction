plugins {
    kotlin("multiplatform") apply false
    kotlin("jvm") apply false
    kotlin("plugin.serialization") apply false
    id("org.jetbrains.kotlinx.kover")
}

allprojects {
    repositories {
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
        maven("https://maven.pkg.jetbrains.space/public/p/ktor/eap")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers")
    }
}

koverMerged {
    enable()

    filters {
        annotations {
            excludes += "*Excluded"
        }
    }
}