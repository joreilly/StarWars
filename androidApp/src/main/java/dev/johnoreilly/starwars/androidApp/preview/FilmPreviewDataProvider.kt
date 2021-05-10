package dev.johnoreilly.starwars.androidApp.preview

import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import dev.johnoreilly.starwars.shared.model.Film

class FilmPreviewDataProvider : CollectionPreviewParameterProvider<Film>(
    listOf(
        Film("1", "ISS", "Chris Cassidy"),
        Film("2", "ISS", "Anatoli Ivanishin")
    )
)