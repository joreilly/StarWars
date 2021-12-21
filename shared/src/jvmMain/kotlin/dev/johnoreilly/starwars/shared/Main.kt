package dev.johnoreilly.starwars.shared

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val repo = StarWarsRepository()
    repo.people.collect {
        println(it)
    }

    repo.films.collect {
        println(it)
    }
}