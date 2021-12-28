package dev.johnoreilly.starwars.shared

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Query
import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.api.NormalizedCacheFactory
import com.apollographql.apollo3.cache.normalized.apolloStore
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.apollographql.apollo3.cache.normalized.watch
import com.apollographql.apollo3.exception.CacheMissException
import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import dev.johnoreilly.starwars.GetAllFilmsQuery
import dev.johnoreilly.starwars.GetAllPeopleQuery
import dev.johnoreilly.starwars.shared.model.mapToModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StarWarsRepository(dbCacheFactory: NormalizedCacheFactory? = null) {
    @NativeCoroutineScope
    private val coroutineScope: CoroutineScope = MainScope()

    private val cacheFactory = MemoryCacheFactory(maxSizeBytes = 10 * 1024 * 1024).let {
        if (dbCacheFactory != null) {
            it.chain(dbCacheFactory)
        } else {
            it
        }
    }

    val apolloClient = ApolloClient.Builder()
        .serverUrl("https://swapi-graphql.netlify.app/.netlify/functions/index")
        .normalizedCache(cacheFactory, writeToCacheAsynchronously = true)
        .build()

    val people = apolloClient.query(GetAllPeopleQuery()).watch().map {
        it.dataAssertNoErrors.allPeople.people.mapNotNull { it?.personFragment?.mapToModel() }
    }

    val films = apolloClient.query(GetAllFilmsQuery()).watch().map {
        it.dataAssertNoErrors.allFilms.films.mapNotNull { it?.filmFragment?.mapToModel() }
    }

    suspend fun prefetch() = withContext(Dispatchers.Default) {
        launch {
            prefetch(GetAllPeopleQuery())
        }
        launch {
            prefetch(GetAllFilmsQuery())
        }
    }

    suspend fun prefetch(query: Query<*>) {
        try {
            apolloClient.apolloStore.readOperation(query)
        } catch (e: CacheMissException) {
            apolloClient.query(query).execute()
        }
    }
}