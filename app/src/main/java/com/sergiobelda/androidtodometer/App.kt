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

package com.sergiobelda.androidtodometer

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
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

        val sharedPref = getSharedPreferences(getString(R.string.shared_preferences_key), Context.MODE_PRIVATE)
        val theme = sharedPref.getInt(getString(R.string.theme), AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        AppCompatDelegate.setDefaultNightMode(theme)
    }
}
