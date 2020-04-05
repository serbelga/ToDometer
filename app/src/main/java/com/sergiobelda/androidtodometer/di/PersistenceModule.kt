package com.sergiobelda.androidtodometer.di

import androidx.room.Room
import com.sergiobelda.androidtodometer.persistence.TodometerDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val persistenceModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            TodometerDatabase::class.java,
            "database"
        ).build()
    }
    single { get<TodometerDatabase>().projectDao() }
    single { get<TodometerDatabase>().taskDao() }
    single { get<TodometerDatabase>().projectTaskViewDao() }
}