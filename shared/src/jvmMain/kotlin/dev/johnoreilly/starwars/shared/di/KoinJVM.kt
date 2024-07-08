package dev.johnoreilly.starwars.shared.di

import com.apollographql.apollo.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo.cache.normalized.api.NormalizedCacheFactory
import com.apollographql.apollo.cache.normalized.sql.SqlNormalizedCacheFactory
import org.koin.dsl.module

actual fun platformModule() = module {
    single<NormalizedCacheFactory> {
        MemoryCacheFactory(10 * 1024 * 1024)
            .chain(SqlNormalizedCacheFactory("jdbc:sqlite:swapi.db"))
    }
}

