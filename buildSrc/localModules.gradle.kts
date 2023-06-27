val json = file("local-modules.json")
val modules = groovy.json.JsonSlurper().parseText(json.readText()) as Map<String, Boolean>
settings.extra.set("modules", modules)

gradle.rootProject {
    this.extra.set("modules", modules)
}

apply {
    plugin(LocalModulesPlugin::class)
}

class LocalModulesPlugin : Plugin<Settings> {

    override fun apply(settings: Settings) {
        val modules = settings.extra.get("modules") as? Map<String, Boolean>
            ?: throw kotlin.Exception("Required extension 'modules' is not setted.")
        modules.forEach { (moduleName, isIncluded) ->
            println(
                """
                AfoxPlus:
                    Module: $moduleName
                    isIncluded: $isIncluded
            """.trimIndent()
            )
            if (isIncluded) {
                val localModule = getLocalModuleByModuleName(moduleName)
                    ?: throw kotlin.Exception("Repository Info for module $moduleName not found.")
                includeLocalModule(settings, localModule)
            }
        }
    }

    private fun getLocalModuleByModuleName(moduleName: String): LocalModule? {
        return LocalModule.values().find { it.moduleName == moduleName }
    }

    private fun includeLocalModule(settings: Settings, localModule: LocalModule) {
        val parentDir = settings.rootProject.projectDir.parent
        val projectDir = getProjectDir(parentDir, localModule)
        settings.include(localModule.moduleName)
        settings.project(":${localModule.moduleName}").projectDir = projectDir

    }

    private fun getProjectDir(parentDir: String, localModule: LocalModule): java.io.File {
        return File("$parentDir/${localModule.folder}/module")
    }

}

enum class LocalModule(
    val moduleName: String,
    val folder: String,
    val branch: String = "develop",
) {
    UIKIT("uikit", "app-android-uikit"),
    NETWORK("network", "app-android-network"),
    AUTH("auth", "app-android-auth"),
    HOME("home", "app-android-home"),
    ORDERS("orders", "app-android-orders"),
    PRODUCTS("products", "app-android-products"),
    RESTAURANTS("restaurants", "app-android-restaurants"),
    WAITER("waiter", "app-android-waiter")
}