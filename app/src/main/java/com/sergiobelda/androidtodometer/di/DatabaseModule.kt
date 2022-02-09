/*
 * Copyright 2020 Sergio Belda
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sergiobelda.androidtodometer.di

import android.app.Application
import androidx.room.Room
import com.sergiobelda.androidtodometer.data.database.MIGRATION_1_2
import com.sergiobelda.androidtodometer.data.database.TodometerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideTodometerDatabase(application: Application) =
        Room.databaseBuilder(application, TodometerDatabase::class.java, "TodometerDatabase.db")
            .createFromAsset("database/AppDatabase.db")
            .addMigrations(MIGRATION_1_2)
            .build()

    @Provides
    fun provideProjectDao(todometerDatabase: TodometerDatabase) = todometerDatabase.projectDao()

    @Provides
    fun provideTaskDao(todometerDatabase: TodometerDatabase) = todometerDatabase.taskDao()

    @Provides
    fun provideProjectTaskViewDao(todometerDatabase: TodometerDatabase) =
        todometerDatabase.projectTaskViewDao()
}
