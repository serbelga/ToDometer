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

package com.sergiobelda.androidtodometer.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.sergiobelda.androidtodometer.data.repository.UserPreferencesRepository.Companion.DATA_STORE_NAME
import com.sergiobelda.androidtodometer.data.repository.UserPreferencesRepository.PreferencesKeys.APP_THEME
import com.sergiobelda.androidtodometer.data.repository.UserPreferencesRepository.PreferencesKeys.PROJECT_SELECTED
import com.sergiobelda.androidtodometer.domain.model.AppThemePreference
import com.sergiobelda.androidtodometer.domain.repository.IUserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

class UserPreferencesRepository(private val context: Context) : IUserPreferencesRepository {

    override fun getProjectSelected(): Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[PROJECT_SELECTED] ?: 1
    }

    override suspend fun setProjectSelected(projectSelected: Int) {
        context.dataStore.edit { preferences ->
            preferences[PROJECT_SELECTED] = projectSelected
        }
    }

    override fun getAppThemePreference(): Flow<AppThemePreference> =
        context.dataStore.data.map { preferences ->
            preferences[APP_THEME]?.let { AppThemePreference.values().getOrNull(it) }
                ?: AppThemePreference.FOLLOW_SYSTEM
        }

    override suspend fun setAppThemePreference(theme: AppThemePreference) {
        context.dataStore.edit { preferences ->
            preferences[APP_THEME] = theme.ordinal
        }
    }

    override suspend fun clearPreferences() {
        context.dataStore.edit {
            it.clear()
        }
    }

    private object PreferencesKeys {
        val PROJECT_SELECTED = intPreferencesKey(PROJECT_SELECTED_KEY)
        val APP_THEME = intPreferencesKey(USER_THEME_KEY)
    }

    companion object {
        const val DATA_STORE_NAME = "preferences"
        private const val PROJECT_SELECTED_KEY = "project_selected"
        private const val USER_THEME_KEY = "user_theme"
    }
}
