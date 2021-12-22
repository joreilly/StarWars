
object Versions {
    const val kotlinVersion = "1.6.10"
    const val apollo = "3.0.0"

    const val kotlinCoroutines = "1.6.0"
    const val ktor = "2.0.0-eap-278"
    const val kotlinxSerialization = "1.3.1"
    const val slf4j = "1.7.30"

    const val compose = "1.1.0-rc01"
    const val composeCompiler = "1.1.0-rc02"
    const val navCompose = "2.4.0-rc01"
    const val accompanist = "0.21.0-beta"

    const val kmpNativeCoroutines = "0.10.0-new-mm"

    const val junit = "4.13"
}


object AndroidSdk {
    const val min = 24
    const val compile = 31
    const val target = compile
}

object Apollo {
    const val apolloRuntime = "com.apollographql.apollo3:apollo-runtime:${Versions.apollo}"
    const val apolloNormalizedCache = "com.apollographql.apollo3:apollo-normalized-cache:${Versions.apollo}"
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
}

object Google {
    object Accompanist {
        const val insets = "com.google.accompanist:accompanist-insets:${Versions.accompanist}"
    }
}

object Ktor {
    const val serverCore = "io.ktor:ktor-server-core:${Versions.ktor}"
    const val serverNetty = "io.ktor:ktor-server-netty:${Versions.ktor}"
    const val serialization = "io.ktor:ktor-serialization:${Versions.ktor}"
    const val clientJson = "io.ktor:ktor-client-json:${Versions.ktor}"
    const val slf4j = "org.slf4j:slf4j-simple:${Versions.slf4j}"
    const val ktorGraphql = "com.apurebase:kgraphql-ktor:0.17.9"
}

object Kotlinx {
    const val serializationCore = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinxSerialization}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
}




