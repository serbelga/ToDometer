package com.sergiobelda.androidtodometer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.sergiobelda.androidtodometer.database.TodometerDatabase
import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.model.Task
import com.sergiobelda.androidtodometer.databaseview.ProjectTaskListing
import com.sergiobelda.androidtodometer.persistence.ProjectRepository
import com.sergiobelda.androidtodometer.persistence.ProjectTaskViewRepository
import com.sergiobelda.androidtodometer.persistence.TaskRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val projectRepository: ProjectRepository
    private val taskRepository: TaskRepository
    private val projectTaskViewRepository: ProjectTaskViewRepository

    val projects: LiveData<PagedList<Project>>
    val projectTaskListingList: LiveData<PagedList<ProjectTaskListing>>

    init {
        val projectDao = TodometerDatabase.getDatabase(application).projectDao()
        projectRepository = ProjectRepository(projectDao)

        val taskDao = TodometerDatabase.getDatabase(application).taskDao()
        taskRepository = TaskRepository(taskDao)

        val projectTaskViewDao = TodometerDatabase.getDatabase(application).projectTaskViewDao()
        projectTaskViewRepository = ProjectTaskViewRepository(projectTaskViewDao)

        projects = projectRepository.projects
        projectTaskListingList = projectTaskViewRepository.projectTaskListingList
    }

    fun getProjectTaskListing(id: Int): LiveData<ProjectTaskListing> {
        return projectTaskViewRepository.getProjectTaskListing(id)
    }

    fun insertTask(task: Task) = viewModelScope.launch {
        taskRepository.insert(task)
    }

    fun deleteTask(id: Int) = viewModelScope.launch {
        taskRepository.deleteTask(id)
    }

    fun insertProject(project: Project) = viewModelScope.launch {
        projectRepository.insert(project)
    }

    fun deleteProject(id: Int) = viewModelScope.launch {
        projectRepository.deleteProject(id)
    }

    fun setTaskDone(id: Int) = viewModelScope.launch {
        taskRepository.setTaskDone(id)
    }

    fun setTaskDoing(id: Int) = viewModelScope.launch {
        taskRepository.setTaskDoing(id)
    }

    fun updateTask(task: Task) = viewModelScope.launch {
        taskRepository.updateTask(task)
    }
}