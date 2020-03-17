package com.sergiobelda.androidtodometer.persistence

import androidx.lifecycle.LiveData
import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.model.ProjectTask
import com.sergiobelda.androidtodometer.databaseview.ProjectTaskFull
import com.sergiobelda.androidtodometer.databaseview.ProjectTaskListing

class ProjectRepository(private val projectDao: ProjectDao) {
    val projects: LiveData<List<Project>> = projectDao.getProjects()

    val projectTasks: LiveData<List<ProjectTask>> = projectDao.getTaskProjects()

    val projectTaskFull: LiveData<List<ProjectTaskFull>> = projectDao.getProjectTaskFull()

    val projectTaskListing: LiveData<List<ProjectTaskListing>> = projectDao.getProjectTaskListing()

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