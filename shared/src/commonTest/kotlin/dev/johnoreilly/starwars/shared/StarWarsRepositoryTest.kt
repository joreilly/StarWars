package dev.johnoreilly.starwars.shared

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.annotations.ApolloExperimental
import com.apollographql.apollo3.api.toResponseJson
import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.apollographql.apollo3.mockserver.MockResponse
import com.apollographql.apollo3.mockserver.MockServer
import dev.johnoreilly.starwars.shared.di.commonModule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
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

    private val mockServer = MockServer()
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

    private fun createMockApolloClient(url: String): ApolloClient {
        println("createMockApolloClient, url = $url")
        return ApolloClient.Builder()
            .serverUrl(url)
            .normalizedCache(MemoryCacheFactory())
            .build()
    }
}

