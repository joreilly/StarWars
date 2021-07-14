
object Versions {
    const val kotlin = "1.5.10"
    const val kotlinxCoroutines = "1.5.0-native-mt"
    const val apollo = "3.0.0-alpha01"

    const val kotlinCoroutines = "1.5.0-native-mt"
    const val ktor = "1.6.0"
    const val kotlinxSerialization = "1.2.1"
    const val slf4j = "1.7.30"

    const val compose = "1.0.0-rc01"
    const val nav_compose = "2.4.0-alpha04"
    const val accompanist = "0.13.0"

    const val junit = "4.13"
}


object AndroidSdk {
    const val min = 24
    const val compile = 30
    const val target = compile
}

object Deps {
    const val apolloRuntime = "com.apollographql.apollo3:apollo-runtime:${Versions.apollo}"
}

object Compose {
    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
    const val uiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
    const val material = "androidx.compose.material:material:${Versions.compose}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.nav_compose}"
}

object Google {
    object Accompanist {
        const val coil = "com.google.accompanist:accompanist-coil:${Versions.accompanist}"
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




