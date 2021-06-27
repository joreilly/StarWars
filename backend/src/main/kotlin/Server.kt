import com.apurebase.kgraphql.GraphQL
import io.ktor.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    val port = System.getenv().getOrDefault("PORT", "8080").toInt()
    embeddedServer(Netty, port) {
        install(GraphQL) {
            playground = true // This adds support for opening the graphql route within the browser
            schema { starWarsSchema() }
        }
    }.start(wait = true)
}
