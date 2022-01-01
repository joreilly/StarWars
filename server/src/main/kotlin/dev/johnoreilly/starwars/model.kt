package dev.johnoreilly.starwars

enum class Episode {
    NEWHOPE, EMPIRE, JEDI
}

interface Character {
    val id : String
    val name : String?
    val friends: List<Character>
    val appearsIn: Set<Episode>
}

data class Human (
    override val id: String,
    override val name: String?,
    override val friends: List<Character>,
    override val appearsIn: Set<Episode>,
    val homePlanet: String,
    val height: Double
) : Character

data class Droid (
    override val id: String,
    override val name: String?,
    override val friends: List<Character>,
    override val appearsIn: Set<Episode>,
    val primaryFunction : String
) : Character


data class Film(val id: String, val title: String, val director: String, val releaseDate: String)

data class Films(val films: List<Film>)

data class Homeworld(val name: String)
data class Person(val id: String, val name: String, val homeworld: Homeworld)

data class People(val people: List<Person>)