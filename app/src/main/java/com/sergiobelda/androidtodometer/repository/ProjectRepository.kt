/*
 * Copyright 2020 Sergio Belda
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

package com.sergiobelda.androidtodometer.repository

import com.sergiobelda.androidtodometer.db.dao.ProjectDao
import com.sergiobelda.androidtodometer.mapper.ProjectMapper.toDomain
import com.sergiobelda.androidtodometer.mapper.ProjectMapper.toEntity
import com.sergiobelda.androidtodometer.model.Project
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProjectRepository(private val projectDao: ProjectDao) {
    val projects: Flow<List<Project>> = projectDao.getProjects().map { list ->
        list.map { it.toDomain() }
    }

    fun getProject(id: Int): Flow<Project> = projectDao.getProject(id).map { it.toDomain() }

    suspend fun deleteProject(id: Int) = projectDao.deleteProject(id)

    suspend fun insert(project: Project) = projectDao.insertProject(project.toEntity())

    suspend fun updateProject(project: Project) = projectDao.updateProject(project.toEntity())
}
