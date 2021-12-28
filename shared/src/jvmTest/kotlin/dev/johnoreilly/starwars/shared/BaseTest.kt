package dev.johnoreilly.starwars.shared

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

actual abstract class BaseTest {
    actual fun <T> runTest(block: suspend CoroutineScope.() -> T) {
        runBlocking { block() }
    }
}
