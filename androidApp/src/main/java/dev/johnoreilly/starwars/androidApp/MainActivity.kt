@file:OptIn(ExperimentalMaterial3Api::class)

package dev.johnoreilly.starwars.androidApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat.setDecorFitsSystemWindows
import dev.johnoreilly.starwars.shared.StarWarsRepository
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDecorFitsSystemWindows(window, false)

        setContent {
            MaterialTheme {
                MainLayout()
            }
        }
    }
}

@Composable
fun MainLayout() {
    val scope = rememberCoroutineScope()
    val repo = remember { StarWarsRepository() }

    val peopleData by repo.peopleData.collectAsState()
    val filmData by repo.filmData.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Star Wars") })
        }
    ) {
        Column(Modifier.padding(it)) {
            Row {
                Button(onClick = {
                    scope.launch { repo.clearData() }
                }) {
                    Text("Clear Data")
                }

                Button(onClick = {
                    scope.launch { getData(repo) }
                }) {
                    Text("Get Data")
                }
            }

            Text("Number of people = ${peopleData.size}")
            Text("Number of films = ${filmData.size}")
        }
    }
}


suspend fun getData(repo: StarWarsRepository) {
    repo.collectPeopleInfo()
    repo.collectFilmInfo()
}