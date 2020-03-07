package com.sergiobelda.androidtodometer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sergiobelda.androidtodometer.database.TodometerDatabase
import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.persistence.ProjectRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val projectRepository: ProjectRepository

    val projects: LiveData<List<Project>>

    init {
        val projectDao = TodometerDatabase.getDatabase(application).projectDao()
        projectRepository = ProjectRepository(projectDao)
        // TODO declare others DAO and initialize its repos
        projects = projectRepository.projects
    }

    fun insertProject(project: Project) = viewModelScope.launch {
        projectRepository.insert(project)
    }

    fun deleteProject(id: Int) = viewModelScope.launch {
        projectRepository.deleteProject(id)
    }
}