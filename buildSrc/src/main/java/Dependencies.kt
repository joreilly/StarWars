
object Versions {
    const val kotlinVersion = "1.8.20"
    const val kotlinCoroutines = "1.6.4"
    const val apollo = "3.7.5"
    const val koinCore = "3.4.0"
    const val koinAndroid = "3.4.0"
    const val koinAndroidCompose = "3.4.3"

    const val compose = "1.4.3"
    const val composeCompiler = "1.4.5"
    const val navCompose = "2.5.2"
    const val accompanist = "0.27.0"
    const val horologist = "0.2.5"

    const val wearCompose = "1.2.0-alpha01"

    const val kmpNativeCoroutines = "0.13.3"

    const val junit = "4.13"
}


object AndroidSdk {
    const val min = 24
    const val minWear = 28
    const val compile = 33
    const val target = compile
}

object Apollo {
    const val apolloRuntime = "com.apollographql.apollo3:apollo-runtime:${Versions.apollo}"
    const val apolloNormalizedCacheInMemory = "com.apollographql.apollo3:apollo-normalized-cache:${Versions.apollo}"
    const val apolloNormalizedCacheSqlite = "com.apollographql.apollo3:apollo-normalized-cache-sqlite:${Versions.apollo}"
    const val apolloMockServer = "com.apollographql.apollo3:apollo-mockserver:${Versions.apollo}"
    const val apolloTestingSupport = "com.apollographql.apollo3:apollo-testing-support:${Versions.apollo}"
}

object Compose {
    const val compiler = "androidx.compose.compiler:compiler:${Versions.composeCompiler}"
    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
    const val uiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
    const val material = "androidx.compose.material:material:${Versions.compose}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.navCompose}"

    const val wearFoundation = "androidx.wear.compose:compose-foundation:${Versions.wearCompose}"
    const val wearMaterial = "androidx.wear.compose:compose-material:${Versions.wearCompose}"
    const val wearNavigation = "androidx.wear.compose:compose-navigation:${Versions.wearCompose}"

    const val activityCompose = "androidx.activity:activity-compose:1.4.0"
}

object Google {
    object Accompanist {
        const val insets = "com.google.accompanist:accompanist-insets:${Versions.accompanist}"
        const val pager = "com.google.accompanist:accompanist-pager:${Versions.accompanist}"
        const val pagerIndicator = "com.google.accompanist:accompanist-pager-indicators:${Versions.accompanist}"
    }
}

object Kotlinx {
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinCoroutines}"
}

object Koin {
    val core = "io.insert-koin:koin-core:${Versions.koinCore}"
    val test = "io.insert-koin:koin-test:${Versions.koinCore}"
    val android = "io.insert-koin:koin-android:${Versions.koinAndroid}"
    val compose = "io.insert-koin:koin-androidx-compose:${Versions.koinAndroidCompose}"
}

object Test {
    const val junit = "junit:junit:${Versions.junit}"
    const val composeUiTest = "androidx.compose.ui:ui-test:${Versions.compose}"
    const val composeUiTestJUnit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
}

object Horologist {
    const val composeLayout = "com.google.android.horologist:horologist-compose-layout:${Versions.horologist}"
}
