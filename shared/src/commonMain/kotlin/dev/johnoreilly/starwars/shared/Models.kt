package dev.johnoreilly.starwars.shared

data class Person(val id: String, val name: String, val homeworld: String?)

data class Film(val id: String, val title: String, val director: String)
