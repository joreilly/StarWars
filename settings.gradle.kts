pluginManagement {
    listOf(repositories, dependencyResolutionManagement.repositories).forEach {
        it.apply {
            google()
            mavenCentral()
            gradlePluginPortal()
            maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
            maven("https://androidx.dev/storage/compose-compiler/repository")
        }
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("com.google.cloud.tools.appengine")) {
                useModule("com.google.cloud.tools:appengine-gradle-plugin:${requested.version}")
            }
        }
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "StarWarsKMM"

include(":androidApp", ":shared")
include(":wearApp")
include(":compose-web")
include(":server")

check(JavaVersion.current().isCompatibleWith(JavaVersion.VERSION_17)) {
    "This project needs to be run with Java 17 or higher (found: ${JavaVersion.current()})."
}
