package dev.johnoreilly.starwars.shared

import dev.johnoreilly.starwars.shared.di.initKoin
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val koin = initKoin().koin
    val repo = koin.get<StarWarsRepository>()

    repo.people.collect {
        println(it)
    }

    repo.films.collect {
        println(it)
    }
}