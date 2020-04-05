package com.sergiobelda.androidtodometer

import android.app.Application
import com.sergiobelda.androidtodometer.di.persistenceModule
import com.sergiobelda.androidtodometer.di.repositoryModule
import com.sergiobelda.androidtodometer.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(persistenceModule)
            modules(repositoryModule)
            modules(viewModelModule)
        }
    }
}