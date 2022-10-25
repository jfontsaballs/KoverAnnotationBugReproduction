package es.test.buildSrc

import org.gradle.api.Project

class MultiplatformLibrary : MultiplatformProjectBase() {
    override fun apply(project: Project) {
        super.apply(project)

        //region Webpack resources
        // Posem el contingut de la carpet resources del source set de JavaScript a disposició del projecte
        // que genera el client, per tal que el webpack ho tingui disponible
        val jsResources = project.configurations.create("jsResources") {
            isCanBeConsumed = true
            isCanBeResolved = false
        }
        project.artifacts {
            add(
                jsResources.name,
                project.tasks.named("jsProcessResources").map { it.outputs.files.files.single() })
        }
        //endregion
    }
}