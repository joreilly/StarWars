package dev.johnoreilly.starwars.wearApp

import android.app.Application
import dev.johnoreilly.starwars.wearApp.di.appModule
import dev.johnoreilly.starwars.shared.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class StarWarsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            // workaround for https://github.com/InsertKoinIO/koin/issues/1188
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@StarWarsApplication)
            modules(appModule)
        }
    }
}