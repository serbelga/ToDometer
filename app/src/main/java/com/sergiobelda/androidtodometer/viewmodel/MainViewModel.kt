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

package com.sergiobelda.androidtodometer.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.sergiobelda.androidtodometer.databaseview.ProjectTaskListing
import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.model.Task
import com.sergiobelda.androidtodometer.repository.ProjectRepository
import com.sergiobelda.androidtodometer.repository.ProjectTaskViewRepository
import com.sergiobelda.androidtodometer.repository.TaskRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val projectRepository: ProjectRepository,
    private val taskRepository: TaskRepository,
    private val projectTaskViewRepository: ProjectTaskViewRepository
) : ViewModel() {

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
