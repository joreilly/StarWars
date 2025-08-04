plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.kotlin.multiplatform.library) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.apollo) apply false
    alias(libs.plugins.kmpNativeCoroutines) apply false
}


// to fix bootJar issue
buildscript {
    configurations.all {
        resolutionStrategy {
            force("org.apache.commons:commons-compress:1.25.0")
        }
    }
}