plugins {
  id("kotlin-platform-jvm")
  id("org.jetbrains.kotlin.plugin.spring").version("1.6.10")
  id("org.jetbrains.kotlin.plugin.serialization").version("1.6.10")
  id("org.springframework.boot").version("3.0.5")
  id("com.google.cloud.tools.appengine").version("2.4.2")
}


dependencies {
  implementation("com.expediagroup:graphql-kotlin-spring-server:6.5.3")
  implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

  testImplementation("com.squareup.okhttp3:okhttp:4.10.0")
}

kotlin {
  sourceSets.all {
    languageSettings {
      optIn("kotlin.RequiresOptIn")
    }
  }
}

appengine {
  stage {
    setArtifact(tasks.named("bootJar").flatMap { (it as Jar).archiveFile })
  }
  deploy {
    projectId = "star-wars"
    version = "GCLOUD_CONFIG"
  }
}