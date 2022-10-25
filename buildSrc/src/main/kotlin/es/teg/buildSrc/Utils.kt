package es.test.buildSrc

import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.getByType

inline fun <reified T : Any> Project.getExtension(block: T.() -> Unit) =
    extensions.getByType<T>().apply(block)

fun kotlinw(target: String): String {
    return "org.jetbrains.kotlin-wrappers:kotlin-$target"
}

inline fun <reified TConfiguration : Any> Project.withExtension(configurationName : String, crossinline block: (TConfiguration) -> Unit) {
    val configuration = extensions.create<TConfiguration>(configurationName)
    afterEvaluate {
        block(configuration)
    }
}