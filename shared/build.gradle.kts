plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.apollographql.apollo3")
    id("com.google.devtools.ksp")
    id("com.rickclephas.kmp.nativecoroutines")
}

kotlin {
    androidTarget()
    jvm()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    wasmJs {
        browser()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.coroutines.core)
            api(libs.koin.core)
            api(libs.koin.compose)

            api(libs.apollo.runtime)
            implementation(libs.apollo.normalized.cache)
        }

        commonTest.dependencies {
            implementation(libs.koin.test)
            implementation(libs.coroutines.test)
            implementation(libs.apollo.mockserver)
            implementation(libs.apollo.testing.support)
            implementation(kotlin("test-common"))
            implementation(kotlin("test-annotations-common"))
        }

        androidMain.dependencies {
            implementation(libs.apollo.normalized.cache.sqlite)
        }

        val androidUnitTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }

        appleMain.dependencies {
            implementation(libs.apollo.normalized.cache.sqlite)
        }

        jvmMain.dependencies {
            implementation(libs.apollo.normalized.cache.sqlite)
        }
    }
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    namespace = "dev.johnoreilly.starwars.shared"
}

apollo {
    service("service") {
        packageName.set("dev.johnoreilly.starwars")
        codegenModels.set("operationBased")
        generateSchema.set(true)
        generateDataBuilders.set(true)
        introspection {
            endpointUrl.set("https://swapi-graphql.netlify.app/.netlify/functions/index")
            schemaFile.set(file("src/commonMain/graphql/schema.graphqls"))
        }
    }
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "17"
}
