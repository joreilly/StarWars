package dev.johnoreilly.starwars.shared

import com.apollographql.apollo3.api.CustomScalarAdapters
import com.apollographql.apollo3.api.json.buildJsonString
import com.apollographql.apollo3.api.json.writeObject
import com.apollographql.apollo3.api.obj
import dev.johnoreilly.starwars.GetAllPeopleQuery
import dev.johnoreilly.starwars.adapter.GetAllPeopleQuery_ResponseAdapter
import dev.johnoreilly.starwars.test.GetAllPeopleQuery_TestBuilder.Data

val getAllPeopleMockResponse = buildJsonString {
  writeObject {
    name("data")
    GetAllPeopleQuery_ResponseAdapter.Data.obj().toJson(
      writer = this,
      customScalarAdapters = CustomScalarAdapters.Empty,
      value = GetAllPeopleQuery.Data {
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
      }
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


