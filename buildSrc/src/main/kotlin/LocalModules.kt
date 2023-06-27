import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.extra

object LocalModules {

    fun setupBuildGradle(
        dependencyHandler: DependencyHandler,
        rootProject: Project,
        mainModule: String
    ) {
        val isLocal = rootProject.extra.has("IS_LOCAL").let {
            if (it) (rootProject.extra.get("IS_LOCAL") as String).toBoolean() else false
        }
        if (isLocal) {
            val modules = rootProject.extra.get("modules") as Map<String, Boolean>
            modules.forEach { (module, isIncluded) ->
                if (isIncluded && !module.equals(mainModule)) {
                    val project = dependencyHandler.project(mapOf("path" to ":$module"))
                    dependencyHandler.add("implementation", project)
                }
            }
        }
    }
}