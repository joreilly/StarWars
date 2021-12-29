package dev.johnoreilly.starwars.shared

import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.api.NormalizedCache
import com.apollographql.apollo3.cache.normalized.api.NormalizedCacheFactory
import dev.johnoreilly.starwars.shared.di.commonModule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue


@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class StarWarsRepositoryTest: KoinTest {

    val repo : StarWarsRepository by inject()

    @BeforeTest
    fun setUp() {
        startKoin{
            modules(
                commonModule(),
                module {
                    single<NormalizedCacheFactory> { FakeCacheFactory() }
                })
        }
    }

    @Test
    fun testStarWars() = runTest {
        val people = repo.people.first()
        assertTrue(people.isNotEmpty())
        println(people)

        val films = repo.films.first()
        assertTrue(films.isNotEmpty())
        println(films)
    }
}

class FakeCacheFactory: NormalizedCacheFactory() {
    override fun create(): NormalizedCache {
        return MemoryCacheFactory().create()
    }
}