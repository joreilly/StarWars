import org.jetbrains.kotlin.gradle.plugin.PLUGIN_CLASSPATH_CONFIGURATION_NAME

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "dev.johnoreilly.starwars.androidApp"
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
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
        useIR = true
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf("-Xallow-jvm-ir-dependencies", "-Xskip-prerelease-check",
            "-Xuse-experimental=com.apollographql.apollo.api.ApolloExperimental"
        )
    }
}


dependencies {
    add(PLUGIN_CLASSPATH_CONFIGURATION_NAME, "androidx.compose.compiler:compiler:${Versions.compose}")
    implementation("androidx.compose.runtime:runtime:${Versions.compose}")

    implementation(project(":shared"))

    implementation(Compose.ui)
    implementation(Compose.uiGraphics)
    implementation(Compose.uiTooling)
    implementation(Compose.foundationLayout)
    implementation(Compose.material)
    implementation(Compose.runtimeLiveData)
    implementation(Compose.navigation)

    implementation(Google.Accompanist.insets)
    implementation(Google.Accompanist.coil)
}
