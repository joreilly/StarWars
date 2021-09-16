package dev.johnoreilly.starwars.shared.model

import dev.johnoreilly.starwars.fragment.FilmFragment

data class Film(val id: String, val title: String, val director: String)

fun FilmFragment.mapToModel() = Film(id, title, director)