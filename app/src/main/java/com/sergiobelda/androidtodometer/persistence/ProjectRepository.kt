package com.sergiobelda.androidtodometer.persistence

import androidx.lifecycle.LiveData
import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.model.ProjectTask

class ProjectRepository(private val projectDao: ProjectDao) {
    val projects: LiveData<List<Project>> = projectDao.getProjects()

    val projectTasks: LiveData<List<ProjectTask>> = projectDao.getTaskProjects()

    suspend fun insert(project: Project) {
        projectDao.insertProject(project)
    }

    suspend fun deleteProjects() {
        projectDao.deleteProjects()
    }

    suspend fun deleteProject(id: Int) {
        projectDao.deleteProject(id)
    }

    suspend fun deleteProject(project: Project) {
        projectDao.deleteProject(project)
    }
}