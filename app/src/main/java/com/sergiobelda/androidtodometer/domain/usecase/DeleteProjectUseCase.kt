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

import com.sergiobelda.androidtodometer.domain.repository.IProjectRepository
import com.sergiobelda.androidtodometer.domain.repository.IUserPreferencesRepository
import kotlinx.coroutines.flow.firstOrNull

class DeleteProjectUseCase(
    private val userPreferencesRepository: IUserPreferencesRepository,
    private val projectRepository: IProjectRepository
) {

    /**
     * Deletes a project. The deleted project will be the current project selected.
     * Once is deleted, it will select the first project in projects list if is not empty.
     */
    suspend operator fun invoke() {
        val projectId = userPreferencesRepository.getProjectSelected().firstOrNull()
        projectId?.let { projectRepository.deleteProject(it) }
        val projects = projectRepository.getProjects().firstOrNull()
        projects?.firstOrNull()?.let { project ->
            userPreferencesRepository.setProjectSelected(project.id)
        }
    }
}
