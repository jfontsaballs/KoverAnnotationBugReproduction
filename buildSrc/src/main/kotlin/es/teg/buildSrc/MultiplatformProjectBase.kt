package es.test.buildSrc

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.provideDelegate
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

abstract class MultiplatformProjectBase : Plugin<Project> {
    override fun apply(project: Project) {
        //region Configuració del compilador Kotlin
        project.pluginManager.apply("org.jetbrains.kotlin.multiplatform")

        project.getExtension<KotlinMultiplatformExtension> {
            jvm {
                compilations.all {
                    kotlinOptions.jvmTarget = "16"
                }
                withJava()
                testRuns["test"].executionTask.configure {
                    useJUnitPlatform()
                }
            }
            js(IR) {
                browser { }
                // Aquí no posem el binaries.executable per guanyar velocitat, les llibreries
                // no cal que generin el JavaScript
            }
        }
        //endregion

        //region Serialització
        project.pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")
        //endregion

        //region Test coverage
        project.pluginManager.apply("org.jetbrains.kotlinx.kover")
        /*project.getExtension<KoverProjectConfig> {
            filters{
                annotations{
                    excludes += "es.teg.util.ExcludeFromCodeCoverage"
                }
            }
        }*/
        //endregion

        //region Kotlin wrappers
        @Suppress("LocalVariableName") val kotlin_wrappers_version: String by project
        project.dependencies {
            // Fem això per assegurar que totes les dependèncis del wrappers siguin de la mateixa versió
            "jsMainImplementation"(enforcedPlatform(kotlinw("wrappers-bom:1.0.0-pre.$kotlin_wrappers_version")))
            "commonMainImplementation"(enforcedPlatform(kotlinw("wrappers-bom:1.0.0-pre.$kotlin_wrappers_version")))
        }
        //endregion
    }
}