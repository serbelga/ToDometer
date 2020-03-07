package com.sergiobelda.androidtodometer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sergiobelda.androidtodometer.database.TodometerDatabase
import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.model.Task
import com.sergiobelda.androidtodometer.model.TaskProject
import com.sergiobelda.androidtodometer.persistence.ProjectRepository
import com.sergiobelda.androidtodometer.persistence.TaskRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val projectRepository: ProjectRepository
    private val taskRepository: TaskRepository

    val projects: LiveData<List<Project>>
    val tasks: LiveData<List<Task>>
    val taskProjects: LiveData<List<TaskProject>>

    init {
        val projectDao = TodometerDatabase.getDatabase(application).projectDao()
        projectRepository = ProjectRepository(projectDao)

        val taskDao = TodometerDatabase.getDatabase(application).taskDao()
        taskRepository = TaskRepository(taskDao)

        projects = projectRepository.projects
        tasks = taskRepository.tasks
        taskProjects = projectRepository.taskProjects
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
}