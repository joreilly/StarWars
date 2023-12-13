package dev.johnoreilly.starwars.shared.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.api.NormalizedCacheFactory
import com.apollographql.apollo3.cache.normalized.normalizedCache
import dev.johnoreilly.starwars.shared.StarWarsRepository
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

expect fun platformModule(): Module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule(), platformModule())
    }

// called by iOS client
fun initKoin() = initKoin() {}

fun commonModule() = module {
    single { StarWarsRepository() }
    single { createApolloClient(get()) }
}

fun createApolloClient(normalizedCacheFactory: NormalizedCacheFactory): ApolloClient {
    return ApolloClient.Builder()
        .serverUrl("https://swapi-graphql.netlify.app/.netlify/functions/index")
        .normalizedCache(normalizedCacheFactory, writeToCacheAsynchronously = true)
        .build()
}

