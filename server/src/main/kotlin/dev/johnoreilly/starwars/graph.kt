package dev.johnoreilly.starwars

import com.expediagroup.graphql.server.operations.Query
import org.springframework.stereotype.Component


val films = listOf(
  Film("ZmlsbXM6MQ==", "A New Hope", "George Lucas", "1977-05-25"),
  Film("ZmlsbXM6Mg==", "The Empire Strikes Back", "Irvin Kershner", "1977-05-25"),
  Film("ZmlsbXM6Mw==", "Return of the Jedi", "Richard Marquand", "1977-05-25"),
)

val people = listOf(
  Person("cGVvcGxlOjE=", "Luke Skywalker", Homeworld("Tatooine")),
  Person("cGVvcGxlOjQ=", "Darth Vader", Homeworld("Tatooine")),
)


@Component
class RootQuery : Query {
  fun allFilms(): Films = Films(films)

  fun allPeople(): People = People(people)
}
