package dev.johnoreilly.starwars.shared

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Query
import com.apollographql.apollo3.cache.normalized.apolloStore
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
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class StarWarsRepository: KoinComponent {
    @NativeCoroutineScope
    private val coroutineScope: CoroutineScope = MainScope()

    private val apolloClient: ApolloClient by inject()

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