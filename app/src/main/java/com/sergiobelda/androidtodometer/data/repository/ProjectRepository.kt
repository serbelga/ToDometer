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

package com.sergiobelda.androidtodometer.data.repository

import com.sergiobelda.androidtodometer.data.localdatasource.IProjectLocalDataSource
import com.sergiobelda.androidtodometer.domain.model.Project
import com.sergiobelda.androidtodometer.domain.repository.IProjectRepository
import kotlinx.coroutines.flow.Flow

class ProjectRepository(
    private val projectLocalDataSource: IProjectLocalDataSource
) : IProjectRepository {

    override fun getProjects(): Flow<List<Project?>> = projectLocalDataSource.getProjects()

    override fun getProject(id: Int): Flow<Project?> = projectLocalDataSource.getProject(id)

    override suspend fun deleteProject(id: Int) {
        projectLocalDataSource.deleteProject(id)
    }

    override suspend fun insert(project: Project): Long? = projectLocalDataSource.insert(project)

    override suspend fun updateProject(project: Project) {
        projectLocalDataSource.updateProject(project)
    }
}
