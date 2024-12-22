package dev.johnoreilly.starwars.shared

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.annotations.ApolloExperimental
import com.apollographql.apollo.api.toResponseJson
import com.apollographql.apollo.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo.cache.normalized.normalizedCache
import com.apollographql.mockserver.MockServer
import com.apollographql.mockserver.MockResponse
import dev.johnoreilly.starwars.shared.di.commonModule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okio.use
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals


@ApolloExperimental
class StarWarsRepositoryTest: KoinTest {
    private val repo : StarWarsRepository by inject()

    private lateinit var mockServerUrl: String

    @BeforeTest
    fun setUp()  {
        startKoin{
            modules(
                commonModule(),
                module {
                    single { createMockApolloClient(mockServerUrl) }
                })
        }
    }

@Test
fun testStarWarsRepository() = runTest {
    MockServer().use { mockServer ->
        mockServerUrl = mockServer.url()

        mockServer.enqueue(MockResponse.Builder().body(getAllPeopleMockResponse.toResponseJson()).build())
        val people = repo.people.first()
        assertEquals(2, people.size)
        assertEquals("Person 1", people[0].name)
        assertEquals("Home World 1", people[0].homeworld.name)
        println(people)

        mockServer.enqueue(MockResponse.Builder().body(getAllFilmsMockResponse.toResponseJson()).build())
        val films = repo.films.first()
        assertEquals(2, films.size)
        assertEquals("Film 1", films[0].title)
        assertEquals("Director 1", films[0].director)
        println(films)
    }

}

    private fun createMockApolloClient(url: String): ApolloClient {
        println("createMockApolloClient, url = $url")
        return ApolloClient.Builder()
            .serverUrl(url)
            .normalizedCache(MemoryCacheFactory())
            .build()
    }
}

