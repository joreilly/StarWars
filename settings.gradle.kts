pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    
}
rootProject.name = "StarWarsKMM"

include(":androidApp", ":shared")
include(":wearApp")
include(":backend")

