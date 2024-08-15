package dev.johnoreilly.starwars.shared

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.cache.normalized.FetchPolicy
import com.apollographql.apollo.cache.normalized.fetchPolicy
import dev.johnoreilly.starwars.GetAllFilmsQuery
import dev.johnoreilly.starwars.GetAllPeopleQuery
import dev.johnoreilly.starwars.fragment.FilmFragment
import dev.johnoreilly.starwars.fragment.PersonFragment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class StarWarsRepository: KoinComponent {
    private val apolloClient: ApolloClient by inject()

    val peopleData: MutableStateFlow<List<PersonFragment>> = MutableStateFlow(emptyList())
    val filmData: MutableStateFlow<List<FilmFragment>> = MutableStateFlow(emptyList())

    suspend fun clearData() {
        peopleData.emit(emptyList())
        filmData.emit(emptyList())
    }

    suspend fun collectPeopleInfo() {
        apolloClient.query(GetAllPeopleQuery())
            .fetchPolicy(FetchPolicy.CacheAndNetwork)
            .toFlowV3()
            .map { it.dataOrThrow().allPeople.people.mapNotNull { it?.personFragment } }
            .catch {
                println("JFOR: collectPeopleInfo, Exception $it")
            }
            .collect { data ->
                peopleData.emit(data)
                println("JFOR: collectPeopleInfo, Data $data")
            }
    }


    suspend fun collectFilmInfo() {
        apolloClient.query(GetAllFilmsQuery())
            .fetchPolicy(FetchPolicy.CacheAndNetwork)
            .toFlowV3()
            .map { it.dataOrThrow().allFilms.films.mapNotNull { it?.filmFragment } }
            .catch {
                println("JFOR: collectFilmInfo, Exception $it")
            }
            .collect { data ->
                filmData.emit(data)
                println("JFOR: collectFilmInfo, Data $data")
            }
    }
}