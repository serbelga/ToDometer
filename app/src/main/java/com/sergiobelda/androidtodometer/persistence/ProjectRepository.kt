package com.sergiobelda.androidtodometer.persistence

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.model.ProjectTask
import kotlinx.coroutines.flow.Flow

class ProjectRepository(private val projectDao: ProjectDao) {
    val projects: LiveData<PagedList<Project>> = projectDao.getProjects().toLiveData(pageSize = 10)

    val projectTasks: Flow<List<ProjectTask>> = projectDao.getTaskProjects()

    suspend fun insert(project: Project) = projectDao.insertProject(project)

    suspend fun deleteProjects() = projectDao.deleteProjects()

    suspend fun deleteProject(id: Int) = projectDao.deleteProject(id)

    suspend fun deleteProject(project: Project) = projectDao.deleteProject(project)
}