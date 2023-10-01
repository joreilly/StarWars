package dev.johnoreilly.starwars.androidApp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import dev.johnoreilly.starwars.fragment.FilmFragment
import dev.johnoreilly.starwars.fragment.PersonFragment
import org.junit.Rule
import org.junit.Test

class StarWarsUITest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val people = listOf(PersonFragment("1", "Name 1", PersonFragment.Homeworld("Home world 1")))
    private val films = listOf(FilmFragment("1", "Title 1", "Director 1"), FilmFragment("2", "Title 2", "Director 2"))

    @Test
    fun testPeopleListScreen() {
        composeTestRule.setContent {
            PeopleList(people)
        }

        val personListNode = composeTestRule.onNodeWithTag(PersonListTag)
        personListNode.assertIsDisplayed()
            .onChildren().assertCountEquals(people.size)

        people.forEachIndexed { index, person ->
            val rowNode = personListNode.onChildAt(index).onChild().onChild()
            rowNode.assertTextContains(person.name)
            rowNode.assertTextContains(person.homeworld.name)
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
            val rowNode = filmListNode.onChildAt(index).onChild().onChild()
            rowNode.assertTextContains(film.title)
            rowNode.assertTextContains(film.director)
        }
    }
}
