package dev.johnoreilly.starwars.androidApp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import dev.johnoreilly.starwars.shared.Film
import dev.johnoreilly.starwars.shared.Person
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

// Compose UI tests run on the JVM with Robolectric (no emulator); the Maestro flow covers the real
// app end to end against the live API. A plain Application keeps the app's start-up out of it.
@RunWith(RobolectricTestRunner::class)
// SDK 35: Robolectric's SDK 36 runtime needs Java 21, and CI runs the tests on 17
@Config(application = android.app.Application::class, sdk = [35])
class StarWarsUITest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val people = listOf(Person("1", "Name 1", "Home world 1"))
    private val films = listOf(Film("1", "Title 1", "Director 1"), Film("2", "Title 2", "Director 2"))

    @Test
    fun testPeopleListScreen() {
        composeTestRule.setContent {
            PeopleList(people)
        }

        val personListNode = composeTestRule.onNodeWithTag(PersonListTag)
        personListNode.assertIsDisplayed()
            .onChildren().assertCountEquals(people.size)

        people.forEachIndexed { index, person ->
            val rowNode = personListNode.onChildAt(index).onChild()
            rowNode.assertTextContains(person.name)
            rowNode.assertTextContains(person.homeworld ?: "")
        }
    }

    @Test
    fun testFilmListScreen() {
        composeTestRule.setContent {
            FilmList(films)
        }

        val filmListNode = composeTestRule.onNodeWithTag(FilmListTag)
        filmListNode.assertIsDisplayed()
            .onChildren().assertCountEquals(films.size)

        films.forEachIndexed { index, film ->
            val rowNode = filmListNode.onChildAt(index).onChild()
            rowNode.assertTextContains(film.title)
            rowNode.assertTextContains(film.director)
        }
    }
}
