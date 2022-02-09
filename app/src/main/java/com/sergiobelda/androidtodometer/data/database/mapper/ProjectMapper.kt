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

import com.sergiobelda.androidtodometer.data.database.entity.ProjectEntity
import com.sergiobelda.androidtodometer.data.database.entity.ProjectTasksRelation
import com.sergiobelda.androidtodometer.data.database.mapper.TaskMapper.toDomain
import com.sergiobelda.androidtodometer.domain.model.Project

object ProjectMapper {
    fun ProjectEntity?.toDomain(): Project? = this?.let {
        Project(
            id = it.id,
            name = it.name,
            description = it.description
        )
    }

    fun ProjectTasksRelation?.toDomain(): Project? = this?.let {
        Project(
            id = it.project.id,
            name = it.project.name,
            description = it.project.description,
            tasks = it.tasks.map { task -> task.toDomain() }.sortedBy { task -> task?.state }
        )
    }

    fun Project?.toEntity(): ProjectEntity? = this?.let {
        ProjectEntity(
            id = it.id,
            name = it.name,
            description = it.description
        )
    }
}
