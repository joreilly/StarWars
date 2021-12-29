package dev.johnoreilly.starwars.wearApp.film

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.ScalingLazyListState
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.rememberScalingLazyListState
import dev.johnoreilly.starwars.fragment.FilmFragment
import dev.johnoreilly.starwars.wearApp.compose.rotaryEventHandler

@Composable
fun FilmList(
    films: List<FilmFragment>,
    scrollState: ScalingLazyListState = rememberScalingLazyListState(),
) {
    val configuration = LocalConfiguration.current
    val verticalPadding = remember {
        if (configuration.isScreenRound) 20.dp else 0.dp
    }

    ScalingLazyColumn(
        modifier = Modifier
            .rotaryEventHandler(scrollState)
            .padding(horizontal = 4.dp),
        contentPadding = PaddingValues(
            horizontal = 8.dp,
            vertical = 8.dp + verticalPadding
        ),
        state = scrollState,
    ) {
        items(films.size) {
            FilmView(films[it])
        }
    }
}

@Composable
fun FilmView(film: FilmFragment) {
    Card(onClick = { }) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(film.title, style = MaterialTheme.typography.title3)
            Text(film.director, style = MaterialTheme.typography.body1)
        }
    }
}
