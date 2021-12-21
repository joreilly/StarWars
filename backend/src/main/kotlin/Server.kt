import com.apurebase.kgraphql.GraphQL
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*


fun main() {
    // commenting out for now until migrated to use ktor 2
//    val port = System.getenv().getOrDefault("PORT", "8080").toInt()
//    embeddedServer(Netty, port) {
//        install(GraphQL) {
//            playground = true // This adds support for opening the graphql route within the browser
//            schema { starWarsSchema() }
//        }
//    }.start(wait = true)
}
