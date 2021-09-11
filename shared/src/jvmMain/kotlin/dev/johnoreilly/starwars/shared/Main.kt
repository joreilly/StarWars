package dev.johnoreilly.starwars.shared

import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val repo = StarWarsRepository()
    val films = repo.getFilms()
    films.forEach {
        println(it)
    }
}