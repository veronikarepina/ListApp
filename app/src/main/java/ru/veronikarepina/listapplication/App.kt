package ru.veronikarepina.listapplication

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.veronikarepina.data.di.dataModule
//import ru.veronikarepina.domain.di.domainModule
import ru.veronikarepina.listapplication.di.appModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(listOf(appModule, dataModule))
        }
    }
}