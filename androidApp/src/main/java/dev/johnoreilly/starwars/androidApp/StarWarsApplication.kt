package dev.johnoreilly.starwars.androidApp

import android.app.Application
import dev.johnoreilly.starwars.androidApp.di.appModule
import dev.johnoreilly.starwars.shared.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class StarWarsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@StarWarsApplication)
            modules(appModule)
        }
    }
}