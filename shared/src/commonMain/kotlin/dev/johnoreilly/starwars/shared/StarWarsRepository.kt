package dev.johnoreilly.starwars.shared

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Query
import com.apollographql.apollo3.cache.normalized.watch
import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import dev.johnoreilly.starwars.GetAllFilmsQuery
import dev.johnoreilly.starwars.GetAllPeopleQuery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.map
import okio.IOException
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class StarWarsRepository: KoinComponent {
    @NativeCoroutineScope
    val coroutineScope: CoroutineScope = MainScope()

    private val apolloClient: ApolloClient by inject()

    @NativeCoroutines
    val people = apolloClient.query(GetAllPeopleQuery()).watch().map {
        it.dataAssertNoErrors.allPeople.people.mapNotNull { it?.personFragment }
    }

    @NativeCoroutines
    val films = apolloClient.query(GetAllFilmsQuery()).watch().map {
        it.dataAssertNoErrors.allFilms.films.mapNotNull { it?.filmFragment }
    }

    suspend fun prefetch() {
        prefetch(GetAllPeopleQuery())
        prefetch(GetAllFilmsQuery())
    }

    private suspend fun prefetch(query: Query<*>) {
        try {
            apolloClient.query(query).execute()
        } catch (ioe: IOException) {
            // ignore prefetch failure
        }
    }
}