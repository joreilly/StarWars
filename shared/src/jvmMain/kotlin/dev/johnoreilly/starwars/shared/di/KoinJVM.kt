package dev.johnoreilly.starwars.shared.di

import com.apollographql.apollo3.cache.normalized.api.NormalizedCacheFactory
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import org.koin.dsl.module

actual fun platformModule() = module {
    single<NormalizedCacheFactory> { SqlNormalizedCacheFactory("jdbc:sqlite:swapi.db") }
}
