/*
 * Copyright 2021 Sergio Belda Galbis
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

package com.sergiobelda.androidtodometer.preferences

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.sergiobelda.androidtodometer.preferences.UserPreferencesRepository.Companion.DATA_STORE_NAME
import com.sergiobelda.androidtodometer.preferences.UserPreferencesRepository.PreferencesKeys.PROJECT_SELECTED
import com.sergiobelda.androidtodometer.preferences.UserPreferencesRepository.PreferencesKeys.USER_THEME
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

class UserPreferencesRepository @Inject constructor(
    @ApplicationContext val context: Context
) {

    fun projectSelected() = context.dataStore.data.map { preferences ->
        preferences[PROJECT_SELECTED] ?: 1
    }

    suspend fun setProjectSelected(projectSelected: Int) {
        context.dataStore.edit { preferences ->
            preferences[PROJECT_SELECTED] = projectSelected
        }
    }

    fun getUserTheme() = context.dataStore.data.map { preferences ->
        preferences[USER_THEME] ?: AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    }

    suspend fun setUserTheme(theme: Int) {
        context.dataStore.edit { preferences ->
            preferences[USER_THEME] = theme
        }
    }

    suspend fun clearPreferences() {
        context.dataStore.edit {
            it.clear()
        }
    }

    private object PreferencesKeys {
        val PROJECT_SELECTED = intPreferencesKey(PROJECT_SELECTED_KEY)
        val USER_THEME = intPreferencesKey(USER_THEME_KEY)
    }

    companion object {
        const val DATA_STORE_NAME = "preferences"
        private const val PROJECT_SELECTED_KEY = "project_selected"
        private const val USER_THEME_KEY = "user_theme"
    }
}
