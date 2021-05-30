buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
        classpath("com.android.tools.build:gradle:7.0.0-beta03")
        classpath("com.apollographql.apollo:apollo-gradle-plugin:${Versions.apollo}")

    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}