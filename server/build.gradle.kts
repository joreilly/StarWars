plugins {
  alias(libs.plugins.kotlinJvm)
  id("org.jetbrains.kotlin.plugin.spring").version("2.2.0")
  id("org.jetbrains.kotlin.plugin.serialization").version("2.2.0")
  id("org.springframework.boot").version("3.5.4")
}


dependencies {
  implementation("com.expediagroup:graphql-kotlin-spring-server:8.8.1")
  implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.7.1")
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")

  //testImplementation("com.squareup.okhttp3:okhttp:4.11.0")
}
