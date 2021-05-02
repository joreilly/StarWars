package dev.johnoreilly.starwars.shared

import GetAllFilmsQuery
import GetAllPeopleQuery
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.http.ApolloHttpNetworkTransport
import fragment.Film
import fragment.Person
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class StarWarsRepository {
    private val scope = MainScope()

    private val apolloClient = ApolloClient(
        networkTransport = ApolloHttpNetworkTransport(
            serverUrl = "https://swapi-graphql.netlify.app/.netlify/functions/index",
            headers = mapOf(
                "Accept" to "application/json",
                "Content-Type" to "application/json",
            )
        )
    )

    fun getPeople(): Flow<List<Person>> {
        return apolloClient.query(GetAllPeopleQuery())
            .execute()
            .map { it.data?.allPeople?.peopleFilterNotNull()?.map { it.fragments.person } ?: emptyList() }
    }

    fun getFilms(): Flow<List<Film>> {
        return apolloClient.query(GetAllFilmsQuery())
            .execute()
            .map { it.data?.allFilms?.filmsFilterNotNull()?.map { it.fragments.film } ?: emptyList() }
    }


    // called from iOS

    fun getPeople(success: (List<Person>) -> Unit)  {
        scope.launch {
            getPeople().collect {
                success(it)
            }
        }
    }

    fun getFilms(success: (List<Film>) -> Unit)  {
        scope.launch {
            getFilms().collect {
                success(it)
            }
        }
    }
}