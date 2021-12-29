package dev.johnoreilly.starwars.shared

val getAllPeopleMockResponse = """
    {
      "data": {
        "allPeople": {
            "people": [
            {
                "__typename": "PeopleConnection",
                "id": "cGVvcGxlOjE=",
                "name": "Luke Skywalker",
                "homeworld": {
                    "name": "Tatooine"
                }
            },
            {
                "__typename": "PeopleConnection",
                "id": "cGVvcGxlOjI=",
                "name": "C-3PO",
                "homeworld": {
                    "name": "Tatooine"
                }
            }                      
            ]
        }
      }
    }
  """.trimIndent()


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

