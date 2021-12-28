package dev.johnoreilly.starwars.wearApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.ScalingLazyListState
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.material.rememberScalingLazyListState
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import dev.johnoreilly.starwars.shared.StarWarsRepository
import dev.johnoreilly.starwars.shared.model.Person
import dev.johnoreilly.starwars.wearApp.compose.rotaryEventHandler
import dev.johnoreilly.starwars.wearApp.film.FilmList
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
    object Lists : Screen("Lists")
}

@Composable
fun MainLayout() {
    val navController = rememberSwipeDismissableNavController()
    val repo = remember { StarWarsRepository() }

    val people by repo.people.collectAsState(emptyList())
    val films by repo.films.collectAsState(emptyList())

    val pagerState = rememberPagerState(initialPage = 0)
    val peopleScrollState = rememberScalingLazyListState()
    val filmScrollState = rememberScalingLazyListState()

    Scaffold(
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
        positionIndicator = {
            if (pagerState.currentPage == 0) {
                PositionIndicator(scalingLazyListState = peopleScrollState)
            } else {
                PositionIndicator(scalingLazyListState = filmScrollState)
            }
        }
    ) {
        SwipeDismissableNavHost(navController, startDestination = Screen.Lists.title) {
            composable(Screen.Lists.title) {
                Box(modifier = Modifier.fillMaxSize()) {
                    HorizontalPager(count = 2, state = pagerState) { page ->
                        when (page) {
                            0 -> PeopleList(people = people, scrollState = peopleScrollState)
                            1 -> FilmList(films = films, scrollState = filmScrollState)
                        }
                    }
                    HorizontalPagerIndicator(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(8.dp),
                        pagerState = pagerState,
                        activeColor = Color.White,
                    )
                }
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
