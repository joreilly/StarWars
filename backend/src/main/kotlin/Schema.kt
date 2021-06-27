import com.apurebase.kgraphql.schema.dsl.SchemaBuilder

val films = listOf(
    Film("ZmlsbXM6MQ==", "A New Hope", "George Lucas", "1977-05-25"),
    Film("ZmlsbXM6Mg==", "The Empire Strikes Back", "Irvin Kershner", "1977-05-25"),
    Film("ZmlsbXM6Mw==", "Return of the Jedi", "Richard Marquand", "1977-05-25"),
)

val people = listOf(
    Person("cGVvcGxlOjE=", "Luke Skywalker", Homeworld("Tatooine")),
    Person("cGVvcGxlOjQ=", "Darth Vader", Homeworld("Tatooine")),
)

fun SchemaBuilder.starWarsSchema() {
    configure {
        useDefaultPrettyPrinter = true
    }

    query("allFilms") {
        resolver{ -> Films(films) }
    }

    query("allPeople") {
        resolver{ -> People(people) }
    }

    type<Film>()
    type<Person>()
}