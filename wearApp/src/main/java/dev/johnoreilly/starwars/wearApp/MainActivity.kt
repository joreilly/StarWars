package dev.johnoreilly.starwars.wearApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.ScalingLazyListState
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.rememberScalingLazyListState
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import dev.johnoreilly.starwars.shared.StarWarsRepository
import dev.johnoreilly.starwars.shared.model.Person
import dev.johnoreilly.starwars.wearApp.compose.rotaryEventHandler
import dev.johnoreilly.starwars.wearApp.theme.StarWarsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StarWarsTheme {
                MainLayout()
            }
        }
    }
}

sealed class Screen(val title: String) {
    object PersonList : Screen("Person List")
}

@Composable
fun MainLayout() {
    val navController = rememberSwipeDismissableNavController()
    val repo = remember { StarWarsRepository() }

    val people by repo.people.collectAsState(emptyList())

    Scaffold {
        SwipeDismissableNavHost(navController, startDestination = Screen.PersonList.title) {
            composable(Screen.PersonList.title) {
                PeopleList(people)
            }
        }
    }
}

@Composable
fun PeopleList(
    people: List<Person>,
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
fun PersonView(person: Person) {
    Card(onClick = { }) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(person.name, style = MaterialTheme.typography.title3)
            Text(
                person.homeWorld,
                style = MaterialTheme.typography.body1,
                color = Color.DarkGray
            )
        }
    }
}
