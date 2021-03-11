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

package com.sergiobelda.androidtodometer.mapper

import com.sergiobelda.androidtodometer.db.entity.TaskEntity
import com.sergiobelda.androidtodometer.model.Task

object TaskMapper {
    fun TaskEntity?.toDomain(): Task? = this?.let {
        Task(
            id = it.taskId,
            name = it.taskName,
            description = it.taskDescription,
            taskState = it.taskState,
            tag = it.tag,
            projectId = it.taskProjectId
        )
    }

    fun Task?.toEntity(): TaskEntity? = this?.let {
        TaskEntity(
            taskId = it.id,
            taskName = it.name,
            taskDescription = it.description,
            taskState = it.taskState,
            tag = it.tag,
            taskProjectId = it.projectId
        )
    }
}
