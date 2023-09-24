package dev.johnoreilly.starwars.wearApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.material.scrollAway
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.layout.ScalingLazyColumnDefaults
import com.google.android.horologist.compose.layout.ScalingLazyColumnState
import com.google.android.horologist.compose.pager.PagerScreen
import dev.johnoreilly.starwars.shared.StarWarsRepository
import dev.johnoreilly.starwars.wearApp.film.FilmList
import dev.johnoreilly.starwars.wearApp.people.PeopleList
import dev.johnoreilly.starwars.wearApp.theme.StarWarsTheme

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            navController = rememberSwipeDismissableNavController()

            StarWarsTheme {
                MainLayout(navController = navController)
            }
        }
    }
}

sealed class Screen(val title: String) {
    data object Lists : Screen("Lists")
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalHorologistApi::class)
@Composable
fun MainLayout(navController: NavHostController) {
    val repo = rememberStarWarsRepository()

    val people by repo.people.collectAsState(emptyList())
    val films by repo.films.collectAsState(emptyList())

    val pagerState = rememberPagerState { 2 }

    Scaffold(vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) }) {
        SwipeDismissableNavHost(
            navController = navController,
            startDestination = Screen.Lists.title
        ) {
            composable(Screen.Lists.title) {
                PagerScreen(state = pagerState) { page ->
                    when (page) {
                        0 -> {
                            val peopleColumnState =
                                ScalingLazyColumnDefaults.belowTimeText(firstItemIsFullWidth = true)
                                    .create()
                            PageScaffold(columnState = peopleColumnState) {
                                PeopleList(people = people, columnState = peopleColumnState)
                            }
                        }

                        1 -> {
                            val filmColumnState =
                                ScalingLazyColumnDefaults.belowTimeText(firstItemIsFullWidth = true)
                                    .create()
                            PageScaffold(columnState = filmColumnState) {
                                FilmList(films = films, columnState = filmColumnState)
                            }
                        }
                    }
                }
            }
        }
    }

}

@OptIn(ExperimentalHorologistApi::class)
@Composable
fun PageScaffold(
    columnState: ScalingLazyColumnState,
    content: @Composable () -> Unit
) {
    Scaffold(
        timeText = { TimeText(modifier = Modifier.scrollAway(columnState.state)) },
        positionIndicator = {
            PositionIndicator(columnState.state)
        }) {
        content()
    }
}

@Composable
private fun rememberStarWarsRepository(): StarWarsRepository {
    val repo = remember { StarWarsRepository() }
    LaunchedEffect(Unit) {
        repo.prefetch()
    }
    return repo
}