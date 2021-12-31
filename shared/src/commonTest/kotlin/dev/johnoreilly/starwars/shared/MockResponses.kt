package dev.johnoreilly.starwars.shared

import com.apollographql.apollo3.api.json.buildJsonString
import com.apollographql.apollo3.api.json.writeAny
import com.apollographql.apollo3.api.json.writeObject
import dev.johnoreilly.starwars.test.GetAllFilmsQuery_TestBuilder
import dev.johnoreilly.starwars.test.GetAllPeopleQuery_TestBuilder

val getAllPeopleMockResponse = buildJsonString {
  writeObject {
    name("data")
    writeAny(
      GetAllPeopleQuery_TestBuilder.DataBuilder().apply {
        allPeople = allPeople {
          people = listOf(
            person {
              id = "1"
              name = "Person 1"
              homeworld = homeworld {
                name = "Home World 1"
              }
            },
            person {
              id = "2"
              name = "Person 2"
              homeworld = homeworld {
                name = "Home World 2"
              }
            }
          )
        }
      }.build()
    )
  }
}

val getAllFilmsMockResponse = buildJsonString {
  writeObject {
    name("data")
    writeAny(
      GetAllFilmsQuery_TestBuilder.DataBuilder().apply {
        allFilms = allFilms {
          films = listOf(
            film {
              id = "1"
              title = "Film 1"
              director = "Director 1"
            },
            film {
              id = "2"
              title = "Film 2"
              director = "Director 1"
            }
          )
        }
      }.build()
    )
  }
}