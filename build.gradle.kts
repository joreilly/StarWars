buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
        classpath("com.android.tools.build:gradle:4.1.3")
        classpath("com.apollographql.apollo:apollo-gradle-plugin:${Versions.apollo}")

    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}