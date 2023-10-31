plugins {
  kotlin("multiplatform")
  id("org.jetbrains.kotlin.plugin.spring").version("1.9.20")
  id("org.jetbrains.kotlin.plugin.serialization").version("1.9.20")
  id("org.springframework.boot").version("3.1.4")
  //id("com.google.cloud.tools.appengine").version("2.4.2")
}


kotlin {
  jvm() {
    withJava()
  }

  sourceSets {
    val jvmMain by getting {
      dependencies {
        implementation("com.expediagroup:graphql-kotlin-spring-server:7.0.2")
        implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.1")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

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