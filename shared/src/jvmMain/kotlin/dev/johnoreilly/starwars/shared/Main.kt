package dev.johnoreilly.starwars.shared

import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val repo = StarWarsRepository(dbCacheFactory = null)

    repo.people.collect {
        println(it)
    }

    repo.films.collect {
        println(it)
    }
}