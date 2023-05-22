plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = AndroidSdk.compile
    defaultConfig {
        applicationId = "dev.johnoreilly.starwars.androidApp"
        minSdk = AndroidSdk.minWear
        targetSdk = AndroidSdk.target

        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    namespace = "dev.johnoreilly.starwars.wearApp"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xskip-prerelease-check",
            "-opt-in=androidx.wear.material.ExperimentalWearMaterialApi",
            "-opt-in=com.google.accompanist.pager.ExperimentalPagerApi",
            "-P",
            "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=true"
        )
    }
}


dependencies {
    implementation(project(":shared"))

    with (Compose) {
        implementation(compiler)
        implementation(ui)
        implementation(uiGraphics)
        implementation(uiTooling)
        implementation(foundationLayout)
        implementation(activityCompose)
        implementation(wearFoundation)
        implementation(wearMaterial)
        implementation(wearNavigation)
    }

    with (Koin) {
        implementation(core)
        implementation(android)
        implementation(compose)
    }

    with (Horologist) {
        implementation(composeLayout)
    }

    implementation(Google.Accompanist.pager)
    implementation(Google.Accompanist.pagerIndicator)
}
