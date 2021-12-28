package dev.johnoreilly.starwars.shared

import com.apollographql.apollo3.annotations.ApolloExperimental
import kotlinx.coroutines.InternalCoroutinesApi
import kotlin.test.Test

class StarWarsRepositoryTest: BaseTest() {


    @ApolloExperimental
    @InternalCoroutinesApi
    @Test
    fun testExample() = runTest {
        val repo = StarWarsRepository()
        repo.people.collect { people ->
            people.forEach {
                println(it.name)
            }
        }

        repo.films.collect { films ->
            films.forEach {
                println(it.title)
            }
        }

    }
}