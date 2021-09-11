package dev.johnoreilly.starwars.androidApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import dev.johnoreilly.starwars.shared.StarWarsRepository
import dev.johnoreilly.starwars.androidApp.theme.StarWarsTheme
import dev.johnoreilly.starwars.shared.Film



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

@Composable
fun MainLayout() {
    val repo = remember { StarWarsRepository() }
    val filmList by produceState(initialValue = emptyList<Film>(), repo) {
        value = repo.getFilms()
    }

    Scaffold(topBar = { TopAppBar (title = { Text("Star Wars") } ) }) {
        Column {
            FilmList(filmList)
        }
    }

}


@Composable
fun FilmList(filmList: List<Film>) {
    LazyColumn {
        items(items = filmList, itemContent = { film ->
            FilmView(film)
        })
    }
}

@Composable
fun FilmView(film: Film) {
    ListItem(
        text = { Text("${film.title} (${film.releaseDate})", style = MaterialTheme.typography.h6) },
        secondaryText = { Text(film.director, style = MaterialTheme.typography.subtitle1, color = Color.DarkGray) }
    )
    Divider()
}
