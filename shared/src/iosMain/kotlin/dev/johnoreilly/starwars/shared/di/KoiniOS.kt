@file:OptIn(kotlin.experimental.ExperimentalObjCRefinement::class)
package dev.johnoreilly.starwars.shared.di

import com.apollographql.apollo.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo.cache.normalized.api.NormalizedCacheFactory
import com.apollographql.apollo.cache.normalized.sql.SqlNormalizedCacheFactory
import org.koin.dsl.module


@HiddenFromObjC
actual fun platformModule() = module {
    single<NormalizedCacheFactory> {
        MemoryCacheFactory(10 * 1024 * 1024)
            .chain(SqlNormalizedCacheFactory("swapi.db"))
    }
}

// called by iOS client (Swift export: returns Unit so Koin types stay out of the Swift API)
fun initKoin() {
    initKoin {}
}
