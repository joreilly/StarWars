import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    kotlin("multiplatform")
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

group = "com.example"
version = "1.0-SNAPSHOT"

@OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
kotlin {
    wasmJs {
        moduleName = "StarWars"
        browser {
            commonWebpackConfig {
                outputFileName = "StarWars.js"
            }
        }

        binaries.executable()
    }
    sourceSets {
        commonMain {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material3)
                api(compose.materialIconsExtended)
                api(compose.components.resources)

                implementation(libs.androidx.navigation.compose)
                implementation(project(":shared"))
            }
        }
    }
}

compose.experimental {
    web.application {}
}

