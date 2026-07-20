plugins {
  alias(libs.plugins.kotlinJvm)
  id("org.jetbrains.kotlin.plugin.spring").version("2.4.10")
  id("org.jetbrains.kotlin.plugin.serialization").version("2.4.10")
  id("org.springframework.boot").version("3.5.16")
}


dependencies {
  implementation("com.expediagroup:graphql-kotlin-spring-server:8.9.1")
  implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.8.0-0.6.x-compat")
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.11.0")

  //testImplementation("com.squareup.okhttp3:okhttp:4.11.0")
}
