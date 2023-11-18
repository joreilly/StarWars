package dev.johnoreilly.starwars.shared

import dev.johnoreilly.starwars.GetAllFilmsQuery
import dev.johnoreilly.starwars.GetAllPeopleQuery
import dev.johnoreilly.starwars.type.buildFilm
import dev.johnoreilly.starwars.type.buildFilmsConnection
import dev.johnoreilly.starwars.type.buildPeopleConnection
import dev.johnoreilly.starwars.type.buildPerson
import dev.johnoreilly.starwars.type.buildPlanet


val getAllPeopleMockResponse = GetAllPeopleQuery.Data {
    allPeople = buildPeopleConnection {
        people = listOf(
            buildPerson {
                id = "1"
                name = "Person 1"
                homeworld = buildPlanet {
                  name = "Home World 1"
                }
            },
            buildPerson {
                id = "2"
                name = "Person 2"
                homeworld = buildPlanet {
                  name = "Home World 2"
                }
            }
        )
    }
}

val getAllFilmsMockResponse = GetAllFilmsQuery.Data {
    allFilms = buildFilmsConnection {
        films = listOf(
            buildFilm {
                id = "1"
                title = "Film 1"
                director = "Director 1"
            },
            buildFilm {
                id = "2"
                title = "Film 2"
                director = "Director 1"
            }
        )
    }
}

