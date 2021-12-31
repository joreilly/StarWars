package dev.johnoreilly.starwars.androidApp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import dev.johnoreilly.starwars.fragment.PersonFragment
import org.junit.Rule
import org.junit.Test

class StarWarsUITest {
    @get:Rule
    val composeTestRule = createComposeRule()

    val people = listOf(PersonFragment("1", "Name 1", PersonFragment.Homeworld("Home world 1")))

    @Test
    fun testPeopleListScreen() {
        composeTestRule.setContent {
            PeopleList(people)
        }

        val personListNode = composeTestRule.onNodeWithTag(PersonListTag)
        personListNode.assertIsDisplayed()
            .onChildren().assertCountEquals(people.size)

        people.forEachIndexed { index, person ->
            val rowNode = personListNode.onChildAt(index)
            rowNode.assertTextContains(person.name)
            rowNode.assertTextContains(person.homeworld.name)
        }
    }
}
