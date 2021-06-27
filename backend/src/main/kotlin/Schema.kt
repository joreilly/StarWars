import com.apurebase.kgraphql.schema.dsl.SchemaBuilder

val luke = Human("2000", "Luke Skywalker", emptyList(), Episode.values().toSet(), "Tatooine", 1.72)

val r2d2 = Droid("2001", "R2-D2", emptyList(), Episode.values().toSet(), "Astromech")

val film = Film("ZmlsbXM6MQ==", "A New Hope", "George Lucas", "1977-05-25")



fun SchemaBuilder.starWarsSchema() {
    //configure method allows you customize schema behaviour
    configure {
        useDefaultPrettyPrinter = true
    }

    // create query "hero" which returns instance of Character
    query("hero") {
        resolver { episode: Episode ->
            when (episode) {
                Episode.NEWHOPE, Episode.JEDI -> r2d2
                Episode.EMPIRE -> luke
            }
        }
    }

    // create query "heroes" which returns list of luke and r2d2
    query("heroes") {
        resolver{ -> listOf(luke, r2d2) }
    }

    query("allFilms") {
        resolver{ -> Films(listOf(film)) }
    }

    // 1kotlin classes need to be registered with "type" method
    // to be included in created schema type system
    // class Character is automatically included,
    // as it is return type of both created queries
    type<Droid>()
    type<Human>()
    type<Film>()
    enum<Episode>()
}