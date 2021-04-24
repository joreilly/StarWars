package dev.johnoreilly.starwars.androidApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.apollographql.apollo.api.ApolloExperimental
import dev.johnoreilly.starwars.shared.StarWarsRepository
import fragment.Film
import fragment.Person

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainLayout()
        }
    }
}

sealed class Screen(val title: String) {
    object PersonList : Screen("Person List")
    object FilmList : Screen("Film List")
}


data class BottomNavigationitem(
    val route: String,
    val icon: ImageVector,
    val iconContentDescription: String
)

val bottomNavigationItems = listOf(
    BottomNavigationitem(Screen.PersonList.title, Icons.Default.Person, Screen.PersonList.title),
    BottomNavigationitem(Screen.FilmList.title, Icons.Filled.Place, Screen.FilmList.title)
)

@Composable
fun MainLayout() {
    val navController = rememberNavController()

    val repo = remember { StarWarsRepository() }
    val people by repo.getPeople().collectAsState(emptyList())
    val filmList by repo.getFilms().collectAsState(emptyList())

    Scaffold(
        topBar = { TopAppBar(title = { Text("Star Wars") } ) },
        bottomBar = { StarWarsBottomNavigation(navController) }
    ) {

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

@Composable
private fun StarWarsBottomNavigation(navController: NavHostController) {

    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)

        bottomNavigationItems.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.iconContentDescription) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo = navController.graph.startDestination
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Composable
fun PeopleList(people: List<Person>) {
    LazyColumn {
        items(people) { person ->
            PersonView(person)
        }
    }
}

@Composable
fun PersonView(person: Person) {
    Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Column {
            Text(text = person.name ?: "", style = TextStyle(fontSize = 20.sp))
            Text(text = person.homeworld?.name ?: "", style = TextStyle(color = Color.DarkGray, fontSize = 14.sp))
        }
    }
    Divider()
}


@Composable
fun FilmList(filmList: List<Film>) {
    LazyColumn {
        items(filmList) { film ->
            FilmView(film)
        }
    }
}


@Composable
fun FilmView(film: Film) {
    Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Column {
            Text(text = film.title ?: "", style = TextStyle(fontSize = 20.sp))
            Text(text = film.director ?: "", style = TextStyle(color = Color.DarkGray, fontSize = 14.sp))
        }
    }
    Divider()
}