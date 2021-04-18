package dev.johnoreilly.starwars.shared

import com.apollographql.apollo.api.ApolloExperimental
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlin.test.Test

class StarWarsRepositoryTest: BaseTest() {

    @ApolloExperimental
    @InternalCoroutinesApi
    @Test
    fun testExample() = runTest {
        val repo = StarWarsRepository()
        repo.getPeople().collect { people ->
            people.forEach {
                println(it.name)
            }
        }

        repo.getFilms().collect { films ->
            films.forEach {
                println(it.title)
            }
        }

    }
}