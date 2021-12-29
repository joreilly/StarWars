package dev.johnoreilly.starwars.androidApp.preview

import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import dev.johnoreilly.starwars.fragment.FilmFragment


class FilmPreviewDataProvider : CollectionPreviewParameterProvider<FilmFragment>(
    listOf(
        FilmFragment("1", "ISS", "Chris Cassidy"),
        FilmFragment("2", "ISS", "Anatoli Ivanishin")
    )
)