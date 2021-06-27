import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("kotlin-platform-jvm")
    application
}

dependencies {
    implementation(Ktor.serverCore)
    implementation(Ktor.serverNetty)
    implementation(Ktor.serialization)
    implementation(Ktor.slf4j)
    implementation(Ktor.ktorGraphql)

    implementation(project(":shared"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("ServerKt")
}