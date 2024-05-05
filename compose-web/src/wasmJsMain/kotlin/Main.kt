import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.CanvasBasedWindow
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.johnoreilly.starwars.fragment.FilmFragment
import dev.johnoreilly.starwars.fragment.PersonFragment
import dev.johnoreilly.starwars.shared.StarWarsRepository
import dev.johnoreilly.starwars.shared.di.initKoin


@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initKoin()

    CanvasBasedWindow("StarWars", canvasElementId = "StarWarsCanvas") {
        MaterialTheme {
            App()
        }
    }
}


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun App() {
    val navController = rememberNavController()
    val repo = rememberStarWarsRepository()

    val windowSizeClass = calculateWindowSizeClass()
    val shouldShowNavRail = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded

    val people by repo.people.collectAsState(emptyList())
    val filmList by repo.films.collectAsState(emptyList())


    Row {
        if (shouldShowNavRail) {
            StarWarsNavigationRail(navController)
        }

        val bottomBar: @Composable () -> Unit = { if (!shouldShowNavRail) { StarWarsBottomNavigation(navController) }  }
        Scaffold(
            topBar = { StarWarsTopAppBar("Star Wars") },
            bottomBar = bottomBar
        ) {
            Column(Modifier.padding(it)) {
                NavHost(navController, startDestination = Screen.PersonList.title) {
                    composable(Screen.PersonList.title) {
                        PeopleList(people)
                    }
                    composable(Screen.FilmList.title) {
                        FilmList(filmList)
                    }
                }
            }
        }
    }
}


sealed class Screen(val title: String) {
    data object PersonList : Screen("Person List")
    data object FilmList : Screen("Film List")
}

data class BottomNavigationItem(
    val route: String,
    val icon: ImageVector,
    val iconContentDescription: String
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(Screen.PersonList.title, Icons.Filled.Person, Screen.PersonList.title),
    BottomNavigationItem(Screen.FilmList.title, Icons.Filled.Movie, Screen.FilmList.title)
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StarWarsTopAppBar(title: String) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

@Composable
private fun StarWarsBottomNavigation(navController: NavHostController) {

    NavigationBar(modifier = Modifier.navigationBarsPadding()) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        bottomNavigationItems.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.iconContentDescription) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route)
                }
            )
        }
    }
}


@Composable
private fun StarWarsNavigationRail(navController: NavHostController) {
    var selectedItem by remember { mutableStateOf(0) }
    NavigationRail(
        modifier = Modifier.safeDrawingPadding(),
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
    ) {
        bottomNavigationItems.forEachIndexed { index, item ->
            NavigationRailItem(
                icon = { Icon(item.icon, contentDescription = item.iconContentDescription) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.route)
                }
            )
        }
    }
}


const val PersonListTag = "PersonList"

@Composable
fun PeopleList(people: List<PersonFragment>) {
    LazyColumn(modifier = Modifier.fillMaxSize().testTag(PersonListTag)) {
        items(people) { person ->
            PersonView(person)
        }
    }
}

@Composable
fun PersonView(person: PersonFragment) {
    Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 4.dp)) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            ListItem(
                headlineContent = {
                    Text(
                        person.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                supportingContent = {
                    Text(
                        person.homeworld.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.DarkGray
                    )
                }
            )
        }
    }
}


const val FilmListTag = "FilmList"

@Composable
fun FilmList(filmList: List<FilmFragment>) {
    LazyColumn(modifier = Modifier.fillMaxSize().testTag(FilmListTag)) {
        items(items = filmList, itemContent = { film ->
            FilmView(film)
        })
    }
}


@Composable
fun FilmView(film: FilmFragment) {
    Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 4.dp)) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            ListItem(
                headlineContent = { Text(film.title, style = MaterialTheme.typography.titleLarge) },
                supportingContent = {
                    Text(
                        film.director,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.DarkGray
                    )
                }
            )
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


