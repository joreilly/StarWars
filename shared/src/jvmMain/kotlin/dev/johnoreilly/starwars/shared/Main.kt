package dev.johnoreilly.starwars.shared

import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val repo = StarWarsRepository()
    val people = repo.getPeople()
    println(people)

    val films = repo.getFilms()
    println(films)
}