rootProject.name = "Energia"

pluginManagement {
    plugins {
        val kotlinVersion = extra["kotlin_version"] as String
        kotlin("multiplatform") version kotlinVersion apply false
        kotlin("jvm") version kotlinVersion apply false
        kotlin("plugin.serialization") version kotlinVersion apply false
        id("org.jetbrains.kotlinx.kover") version "0.6.1"
    }
}

include("_llibreries:util")
include("_llibreries:web")
include("_llibreries:scada")
include("shared")
include("client")
include("server")
include("lib")
