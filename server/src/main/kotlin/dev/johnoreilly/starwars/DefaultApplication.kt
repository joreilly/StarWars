package dev.johnoreilly.starwars

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext

@SpringBootApplication
class DefaultApplication {
}

fun runServer(): ConfigurableApplicationContext {
  return runApplication<DefaultApplication>()
}



