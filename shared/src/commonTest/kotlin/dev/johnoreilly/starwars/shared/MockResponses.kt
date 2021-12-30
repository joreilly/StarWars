package dev.johnoreilly.starwars.shared

import com.apollographql.apollo3.api.json.buildJsonString
import com.apollographql.apollo3.api.json.writeAny
import com.apollographql.apollo3.api.json.writeObject
import dev.johnoreilly.starwars.test.GetAllPeopleQuery_TestBuilder

val getAllPeopleMockResponse = buildJsonString {
  writeObject {
    name("data")
    writeAny(
      GetAllPeopleQuery_TestBuilder.DataBuilder().apply {
        allPeople = allPeople {
          people = listOf(
            person {
              id = "cGVvcGxlOjE="
              name = "Luke Skywalker"
              homeworld = homeworld {
                name = "Tatooine"
              }
            },
            person {
              id = "cGVvcGxlOjI="
              name = "C-3PO"
              homeworld = homeworld {
                name = "Tatooine"
              }
            }
          )
        }
      }.build()
    )
  }
}

val getAllFilmsMockResponse = """
    {
      "data": {
        "allFilms": {
            "films": [
            {
                "__typename": "FilmsConnection",
                "id": "ZmlsbXM6MQ==",
                "title": "A New Hope",
                "director": "George Lucas"     
            },
            {
                "__typename": "FilmsConnection",
                "id": "ZmlsbXM6Mg==",
                "title": "The Empire Strikes Back",
                "director": "Irvin Kershner"
            }                      
            ]
        }
      }
    }
  """.trimIndent()


