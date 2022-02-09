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
import com.sergiobelda.androidtodometer.data.database.mapper.ProjectMapper.toDomain
import com.sergiobelda.androidtodometer.data.database.mapper.ProjectMapper.toEntity
import com.sergiobelda.androidtodometer.domain.model.Project
import org.junit.Assert.assertEquals
import org.junit.Test

class ProjectMapperTest {

    @Test
    fun `Project to ProjectEntity`() {
        val project = Project(1, "Name", "Description")
        val projectEntity = project.toEntity()
        assertEquals(project.id, projectEntity?.id)
        assertEquals(project.name, projectEntity?.name)
        assertEquals(project.description, projectEntity?.description)
    }

    @Test
    fun `ProjectEntity to Project`() {
        val projectEntity = ProjectEntity(1, "Name", "Description")
        val project = projectEntity.toDomain()
        assertEquals(projectEntity.id, project?.id)
        assertEquals(projectEntity.name, project?.name)
        assertEquals(projectEntity.description, project?.description)
    }

    @Test
    fun `ProjectTaskRelation to Project`() {
        val projectEntity = ProjectEntity(1, "Name", "Description")
        val projectTasksRelation = ProjectTasksRelation(projectEntity, emptyList())
        val project = projectTasksRelation.toDomain()
        assertEquals(projectTasksRelation.project.id, project?.id)
        assertEquals(projectTasksRelation.project.name, project?.name)
        assertEquals(projectTasksRelation.project.description, project?.description)
    }
}
