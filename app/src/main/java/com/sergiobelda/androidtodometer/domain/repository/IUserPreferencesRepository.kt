/*
 * Copyright 2022 Sergio Belda Galbis
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

package com.sergiobelda.androidtodometer.domain.repository

import com.sergiobelda.androidtodometer.domain.model.AppThemePreference
import kotlinx.coroutines.flow.Flow

interface IUserPreferencesRepository {

    fun getProjectSelected(): Flow<Int>

    suspend fun setProjectSelected(projectSelected: Int)

    fun getAppThemePreference(): Flow<AppThemePreference>

    suspend fun setAppThemePreference(theme: AppThemePreference)

    suspend fun clearPreferences()
}
