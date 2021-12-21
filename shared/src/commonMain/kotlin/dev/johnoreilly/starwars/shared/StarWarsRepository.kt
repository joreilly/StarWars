package dev.johnoreilly.starwars.shared

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.apollographql.apollo3.cache.normalized.watch
import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import dev.johnoreilly.starwars.GetAllFilmsQuery
import dev.johnoreilly.starwars.GetAllPeopleQuery
import dev.johnoreilly.starwars.shared.model.mapToModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.map

class StarWarsRepository {
    @NativeCoroutineScope
    private val coroutineScope: CoroutineScope = MainScope()

    private val cacheFactory = MemoryCacheFactory(maxSizeBytes = 10 * 1024 * 1024)
    private val apolloClient = ApolloClient.Builder()
        .serverUrl("https://swapi-graphql.netlify.app/.netlify/functions/index")
        .normalizedCache(cacheFactory)
        .build()


    val people = apolloClient.query(GetAllPeopleQuery()).watch().map {
        it.dataAssertNoErrors.allPeople.people.mapNotNull { it?.personFragment?.mapToModel() }
    }

    val films = apolloClient.query(GetAllFilmsQuery()).watch().map {
        it.dataAssertNoErrors.allFilms.films.mapNotNull { it?.filmFragment?.mapToModel() }
    }
}