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

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.persistence.ProjectDao

class ProjectRepository(private val projectDao: ProjectDao) {
    val projects: LiveData<PagedList<Project>> = projectDao.getProjects().toLiveData(pageSize = 10)

    fun getProject(id: Int) = projectDao.getProject(id)

    suspend fun deleteProject(id: Int) = projectDao.deleteProject(id)

    suspend fun insert(project: Project) = projectDao.insertProject(project)

    suspend fun updateProject(project: Project) = projectDao.updateProject(project)
}
