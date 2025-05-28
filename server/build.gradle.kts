plugins {
  kotlin("multiplatform")
  id("org.jetbrains.kotlin.plugin.spring").version("2.0.20")
  id("org.jetbrains.kotlin.plugin.serialization").version("2.0.20")
  id("org.springframework.boot").version("3.3.4")
}


kotlin {
  jvm() {
    withJava()
  }

  sourceSets {
    val jvmMain by getting {
      dependencies {
        implementation("com.expediagroup:graphql-kotlin-spring-server:7.4.0")
        implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.1")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

        //testImplementation("com.squareup.okhttp3:okhttp:4.11.0")
      }
    }
  }
}

//appengine {
//  stage {
//    setArtifact(tasks.named("bootJar").flatMap { (it as Jar).archiveFile })
//  }
//  deploy {
//    projectId = "star-wars"
//    version = "GCLOUD_CONFIG"
//  }
//}