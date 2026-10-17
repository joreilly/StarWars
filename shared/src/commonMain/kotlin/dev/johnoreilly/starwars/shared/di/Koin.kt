@file:OptIn(kotlin.experimental.ExperimentalObjCRefinement::class)
package dev.johnoreilly.starwars.shared.di

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.cache.normalized.api.NormalizedCacheFactory
import com.apollographql.apollo.cache.normalized.normalizedCache
import dev.johnoreilly.starwars.shared.StarWarsRepository
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import kotlin.native.HiddenFromObjC

@HiddenFromObjC
expect fun platformModule(): Module

@HiddenFromObjC
fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule(), platformModule())
    }

@HiddenFromObjC
fun commonModule() = module {
    single { StarWarsRepository() }
    single { createApolloClient(get()) }
}

internal fun createApolloClient(normalizedCacheFactory: NormalizedCacheFactory): ApolloClient {
    return ApolloClient.Builder()
        .serverUrl("https://swapi-graphql.netlify.app/graphql")
        .normalizedCache(normalizedCacheFactory, writeToCacheAsynchronously = true)
        .build()
}

