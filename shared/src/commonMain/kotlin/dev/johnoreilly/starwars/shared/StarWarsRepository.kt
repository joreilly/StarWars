package dev.johnoreilly.starwars.shared

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.watch
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
            apolloClient.query(GetAllPeopleQuery()).execute()
        }
        launch {
            apolloClient.query(GetAllFilmsQuery()).execute()
        }
    }

}