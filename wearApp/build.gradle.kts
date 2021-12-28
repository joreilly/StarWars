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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf("-Xallow-jvm-ir-dependencies", "-Xskip-prerelease-check",
            "-Xuse-experimental=com.apollographql.apollo.api.ApolloExperimental",
            "-opt-in=androidx.wear.material.ExperimentalWearMaterialApi",
            "-opt-in=com.google.accompanist.pager.ExperimentalPagerApi"
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

    implementation(Google.Accompanist.pager)
    implementation(Google.Accompanist.pagerIndicator)
}
