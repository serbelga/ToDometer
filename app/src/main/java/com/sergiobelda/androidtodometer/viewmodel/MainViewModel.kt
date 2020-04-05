package com.sergiobelda.androidtodometer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.model.Task
import com.sergiobelda.androidtodometer.databaseview.ProjectTaskListing
import com.sergiobelda.androidtodometer.repository.ProjectRepository
import com.sergiobelda.androidtodometer.repository.ProjectTaskViewRepository
import com.sergiobelda.androidtodometer.repository.TaskRepository
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application,
    private val projectRepository: ProjectRepository,
    private val taskRepository: TaskRepository,
    private val projectTaskViewRepository: ProjectTaskViewRepository
) : AndroidViewModel(application) {

    val projects: LiveData<PagedList<Project>> = projectRepository.projects
    val projectTaskListingList: LiveData<PagedList<ProjectTaskListing>> = projectTaskViewRepository.projectTaskListingList

    fun getProjectTaskListing(id: Int): LiveData<ProjectTaskListing> = projectTaskViewRepository.getProjectTaskListing(id).asLiveData()

    fun getProject(id: Int): LiveData<Project> = projectRepository.getProject(id).asLiveData()

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

    fun updateProject(project: Project) = viewModelScope.launch {
        projectRepository.updateProject(project)
    }
}