package dev.johnoreilly.starwars.shared

import com.apollographql.apollo3.ApolloClient
import dev.johnoreilly.starwars.shared.fragment.FilmFragment
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

data class Film(val id: String, val title: String, val director: String, val releaseDate: String)

fun FilmFragment.mapToModel() = Film(id, title, director, releaseDate)


class StarWarsRepository {
    private val serverUrl = "https://swapi-graphql.netlify.app/.netlify/functions/index"
    //private val serverUrl = "http://10.0.2.2:8080/graphql"
    private val apolloClient = ApolloClient(serverUrl)


    suspend fun getFilms(): List<Film> {
        val response = apolloClient.query(GetAllFilmsQuery())
        return response.dataOrThrow.allFilms.films.mapNotNull { it?.filmFragment?.mapToModel() }
    }


    // used by iOS client
    private val scope = MainScope()

    fun getFilms(success: (List<Film>) -> Unit)  {
        scope.launch {
            success(getFilms())
        }
    }


}