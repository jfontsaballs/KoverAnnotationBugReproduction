@file:Suppress("UNUSED_VARIABLE", "PropertyName")

val kotlin_version : String by project
val kotlinx_coroutines_version : String by project
val kotlinx_atomicfu_version : String by project

plugins {
    id("multiplatform-library")
}

group = "es.test"
version = "1.0"

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinx_coroutines_version")
                implementation("org.jetbrains.kotlin:atomicfu:1.6.21")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("com.willowtreeapps.assertk:assertk:0.25")
            }
        }
        val jvmMain by getting
        val jvmTest by getting
        val jsMain by getting
        val jsTest by getting
    }
}
