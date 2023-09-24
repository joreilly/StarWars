@file:OptIn(ExperimentalHorologistApi::class)

package dev.johnoreilly.starwars.wearApp.film

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.layout.ScalingLazyColumnState
import dev.johnoreilly.starwars.fragment.FilmFragment

@Composable
fun FilmList(
    films: List<FilmFragment>,
    columnState: ScalingLazyColumnState,
) {
    ScalingLazyColumn(
        modifier = Modifier,
        columnState = columnState,
    ) {
        items(films.size) {
            FilmView(films[it])
        }
    }
}

@Composable
fun FilmView(film: FilmFragment) {
    Card(modifier = Modifier.fillMaxWidth(), onClick = { }) {
        Text(film.title, style = MaterialTheme.typography.title3)
        Text(
            film.director,
            style = MaterialTheme.typography.body1
        )
    }
}
