plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.apollo)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kmpNativeCoroutines)
}

kotlin {
    androidLibrary {
        namespace = "dev.johnoreilly.starwars.shared"
        compileSdk = libs.versions.compileSdk.get().toInt()
        minSdk = libs.versions.minSdk.get().toInt()
    }

    jvmToolchain(17)

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
            implementation(libs.kotlinx.coroutines)
            api(libs.koin.core)
            api(libs.koin.compose)

            api(libs.apollo.runtime)
            implementation(libs.apollo.normalized.cache)
        }

        commonTest.dependencies {
            implementation(libs.koin.test)
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.apollo.mockserver)
            implementation(libs.apollo.testing.support)
            implementation(kotlin("test"))
        }

        androidMain.dependencies {
            implementation(libs.apollo.normalized.cache.sqlite)
        }

        appleMain.dependencies {
            implementation(libs.apollo.normalized.cache.sqlite)
        }

        jvmMain.dependencies {
            implementation(libs.apollo.normalized.cache.sqlite)
        }
    }
}

apollo {
    service("service") {
        packageName.set("dev.johnoreilly.starwars")
        codegenModels.set("operationBased")
        generateSchema.set(true)
        generateDataBuilders.set(true)
        introspection {
            endpointUrl.set("https://swapi-graphql.netlify.app/graphql")
            schemaFile.set(file("src/commonMain/graphql/schema.graphqls"))
        }
    }
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
}
