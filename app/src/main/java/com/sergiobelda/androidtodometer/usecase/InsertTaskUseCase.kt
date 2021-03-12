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

package com.sergiobelda.androidtodometer.usecase

import com.sergiobelda.androidtodometer.model.Tag
import com.sergiobelda.androidtodometer.model.Task
import com.sergiobelda.androidtodometer.model.TaskState
import com.sergiobelda.androidtodometer.preferences.UserPreferencesRepository
import com.sergiobelda.androidtodometer.repository.TaskRepository
import kotlinx.coroutines.flow.firstOrNull

class InsertTaskUseCase(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val taskRepository: TaskRepository
) {

    /**
     * Creates a new task in the current project selected.
     *
     * @param name Task name.
     * @param description Task description.
     * @param tag Task tag.
     * @param taskState Task state.
     * @return Id of new task, null if the current project selected is null or could not be created.
     */
    suspend operator fun invoke(
        name: String,
        description: String,
        tag: Tag,
        taskState: TaskState
    ): Long? =
        userPreferencesRepository.projectSelected().firstOrNull()?.let {
            taskRepository.insert(
                Task(
                    name = name,
                    description = description,
                    projectId = it,
                    tag = tag,
                    state = taskState
                )
            )
        }
}
