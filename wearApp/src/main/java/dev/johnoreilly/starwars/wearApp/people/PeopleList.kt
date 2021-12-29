package dev.johnoreilly.starwars.wearApp.people

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.ScalingLazyListState
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.rememberScalingLazyListState
import dev.johnoreilly.starwars.fragment.PersonFragment
import dev.johnoreilly.starwars.wearApp.compose.rotaryEventHandler

@Composable
fun PeopleList(
    people: List<PersonFragment>,
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
        items(people.size) {
            PersonView(people[it])
        }
    }
}

@Composable
fun PersonView(person: PersonFragment) {
    Card(onClick = { }) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(person.name, style = MaterialTheme.typography.title3)
            Text(
                person.homeworld.name,
                style = MaterialTheme.typography.body1,
                color = Color.DarkGray
            )
        }
    }
}
