package dev.johnoreilly.starwars.wearApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import dev.johnoreilly.starwars.shared.StarWarsRepository
import dev.johnoreilly.starwars.wearApp.film.FilmList
import dev.johnoreilly.starwars.wearApp.people.PeopleList
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

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun MainLayout() {
    val navController = rememberSwipeDismissableNavController()
    val repo = rememberStarWarsRepository()

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
private fun rememberStarWarsRepository(): StarWarsRepository {
    val repo = remember { StarWarsRepository() }
    LaunchedEffect(Unit) {
        repo.prefetch()
    }
    return repo
}