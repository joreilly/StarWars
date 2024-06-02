package dev.johnoreilly.starwars.shared

import dev.johnoreilly.starwars.shared.di.initKoin
import kotlinx.coroutines.flow.first


suspend fun main()  {
    val koin = initKoin().koin
    val repo = koin.get<StarWarsRepository>()

    val people = repo.people.first()
    println(people)

    val films = repo.films.first()
    println(films)
}