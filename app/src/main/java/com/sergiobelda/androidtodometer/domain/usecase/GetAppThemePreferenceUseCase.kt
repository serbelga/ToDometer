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

package com.sergiobelda.androidtodometer.domain.usecase

import com.sergiobelda.androidtodometer.domain.model.AppThemePreference
import com.sergiobelda.androidtodometer.domain.repository.IUserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAppThemePreferenceUseCase @Inject constructor(
    userPreferencesRepository: IUserPreferencesRepository
) {

    /**
     * Retrieves the current selected theme in user preferences
     * every time it changes.
     */
    val appThemePreference: Flow<AppThemePreference> =
        userPreferencesRepository.getAppThemePreference()
}
