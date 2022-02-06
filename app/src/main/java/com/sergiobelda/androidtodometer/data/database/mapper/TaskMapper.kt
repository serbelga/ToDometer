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

package com.sergiobelda.androidtodometer.data.database.mapper

import com.sergiobelda.androidtodometer.data.database.entity.TaskEntity
import com.sergiobelda.androidtodometer.domain.model.Task

object TaskMapper {
    fun TaskEntity?.toDomain(): Task? = this?.let {
        Task(
            id = it.id,
            name = it.name,
            description = it.description,
            state = it.state,
            tag = it.tag,
            projectId = it.projectId
        )
    }

    fun Task?.toEntity(): TaskEntity? = this?.let {
        TaskEntity(
            id = it.id,
            name = it.name,
            description = it.description,
            state = it.state,
            tag = it.tag,
            projectId = it.projectId
        )
    }
}
