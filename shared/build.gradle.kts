plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.apollographql.apollo3")
    id("com.google.devtools.ksp")
    id("com.rickclephas.kmp.nativecoroutines")
}

kotlin {
    android()
    jvm()

    val iosArm64 = iosArm64()
    val iosX64 = iosX64()
    val iosSimulatorArm64 = iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(Kotlinx.coroutinesCore)

                api(Koin.core)

                with(Apollo) {
                    api(apolloRuntime)
                    implementation(apolloNormalizedCacheInMemory)
                    implementation(apolloNormalizedCacheSqlite)
                }
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(Koin.test)
                implementation(Kotlinx.coroutinesTest)
                implementation(Apollo.apolloMockServer)
                implementation(Apollo.apolloTestingSupport)
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
            }
        }

        val appleMain by creating {
            dependsOn(commonMain)
        }
        val appleTest by creating {
            dependsOn(commonTest)
        }

        val jvmMain by getting

        listOf(
            iosArm64, iosX64, iosSimulatorArm64
        ).forEach {
            it.binaries.framework {
                baseName = "shared"
            }
            getByName("${it.targetName}Main") {
                dependsOn(appleMain)
            }
            getByName("${it.targetName}Test") {
                dependsOn(appleTest)
            }
        }

    }
}

android {
    compileSdk = AndroidSdk.compile
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target
    }

}

apollo {
    packageName.set("dev.johnoreilly.starwars")
    codegenModels.set("operationBased")
    generateSchema.set(true)
    generateTestBuilders.set(true)
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
}
