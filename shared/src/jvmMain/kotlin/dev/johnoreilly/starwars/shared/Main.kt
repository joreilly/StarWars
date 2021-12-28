package dev.johnoreilly.starwars.shared

import dev.johnoreilly.starwars.shared.di.initKoin
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val koin = initKoin().koin
    val repo = koin.get<StarWarsRepository>()

    val people = repo.people.first()
    println(people)

    val films = repo.films.first()
    println(films)
}