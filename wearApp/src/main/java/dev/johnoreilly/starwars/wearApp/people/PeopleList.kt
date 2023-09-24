@file:OptIn(ExperimentalHorologistApi::class)

package dev.johnoreilly.starwars.wearApp.people

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.layout.ScalingLazyColumnState
import dev.johnoreilly.starwars.fragment.PersonFragment

@Composable
fun PeopleList(
    people: List<PersonFragment>,
    columnState: ScalingLazyColumnState,
) {
    ScalingLazyColumn(
        columnState = columnState,
    ) {
        items(people.size) {
            PersonView(people[it])
        }
    }
}

@Composable
fun PersonView(person: PersonFragment) {
    Card(modifier = Modifier.fillMaxWidth(), onClick = { }) {
        Text(person.name, style = MaterialTheme.typography.title3)
        Text(
            person.homeworld.name,
            style = MaterialTheme.typography.body1
        )
    }
}
