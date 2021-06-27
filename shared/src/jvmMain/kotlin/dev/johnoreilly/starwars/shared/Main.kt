package dev.johnoreilly.starwars.shared

import com.apollographql.apollo.api.ApolloExperimental
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

@ApolloExperimental
fun main() = runBlocking {

    val repo = StarWarsRepository()
    repo.getPeople().collect { people ->
        people.forEach {
            println(it.name)
        }
    }

    repo.getFilms().collect { films ->
        films.forEach {
            println(it)
        }
    }
}