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

import com.sergiobelda.androidtodometer.domain.model.Project
import com.sergiobelda.androidtodometer.domain.repository.IProjectRepository
import com.sergiobelda.androidtodometer.domain.repository.IUserPreferencesRepository

class InsertProjectUseCase(
    private val userPreferencesRepository: IUserPreferencesRepository,
    private val projectRepository: IProjectRepository
) {

    /**
     * Creates a new project and set it as the project selected.
     *
     * @param name Project name.
     * @param description Project description.
     *
     * @return Id of new project, null if it could not be created.
     */
    suspend operator fun invoke(name: String, description: String): Long? =
        projectRepository.insert(Project(name = name, description = description))?.let {
            userPreferencesRepository.setProjectSelected(it.toInt())
            it
        }
}
