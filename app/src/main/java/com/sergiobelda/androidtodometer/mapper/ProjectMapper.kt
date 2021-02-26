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

import com.sergiobelda.androidtodometer.db.entity.ProjectEntity
import com.sergiobelda.androidtodometer.db.entity.ProjectTasksRelation
import com.sergiobelda.androidtodometer.mapper.TaskMapper.toDomain
import com.sergiobelda.androidtodometer.model.Project

object ProjectMapper {
    fun ProjectEntity.toDomain(): Project =
        Project(
            id = this.projectId,
            name = this.projectName,
            description = this.projectDescription
        )

    fun ProjectTasksRelation.toDomain(): Project =
        Project(
            id = this.project.projectId,
            name = this.project.projectName,
            description = this.project.projectDescription,
            tasks = this.tasks.map { it.toDomain() }
        )

    fun Project.toEntity(): ProjectEntity =
        ProjectEntity(
            projectId = this.id,
            projectName = this.name,
            projectDescription = this.description
        )
}
