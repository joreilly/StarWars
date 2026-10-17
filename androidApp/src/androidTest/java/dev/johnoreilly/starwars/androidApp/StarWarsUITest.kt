package dev.johnoreilly.starwars.androidApp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import dev.johnoreilly.starwars.shared.Film
import dev.johnoreilly.starwars.shared.Person
import org.junit.Rule
import org.junit.Test

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
