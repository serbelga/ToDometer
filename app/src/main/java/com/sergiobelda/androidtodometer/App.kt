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
import androidx.appcompat.app.AppCompatDelegate
import com.sergiobelda.androidtodometer.preferences.UserPreferencesRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var userPreferencesRepository: UserPreferencesRepository

    private val appCoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    companion object {
        const val PACKAGE = "com.sergiobelda.androidtodometer"
    }

    override fun onCreate() {
        super.onCreate()
        appCoroutineScope.launch {
            AppCompatDelegate.setDefaultNightMode(
                userPreferencesRepository.getUserTheme().firstOrNull()
                    ?: AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            )
        }
    }
}
