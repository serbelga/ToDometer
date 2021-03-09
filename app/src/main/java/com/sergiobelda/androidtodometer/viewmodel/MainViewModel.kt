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

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.model.Tag
import com.sergiobelda.androidtodometer.model.Task
import com.sergiobelda.androidtodometer.model.TaskState
import com.sergiobelda.androidtodometer.usecase.DeleteProjectUseCase
import com.sergiobelda.androidtodometer.usecase.DeleteTaskUseCase
import com.sergiobelda.androidtodometer.usecase.GetAppThemeUseCase
import com.sergiobelda.androidtodometer.usecase.GetProjectSelectedIdUseCase
import com.sergiobelda.androidtodometer.usecase.GetProjectSelectedUseCase
import com.sergiobelda.androidtodometer.usecase.GetProjectsUseCase
import com.sergiobelda.androidtodometer.usecase.GetTaskUseCase
import com.sergiobelda.androidtodometer.usecase.InsertProjectUseCase
import com.sergiobelda.androidtodometer.usecase.InsertTaskUseCase
import com.sergiobelda.androidtodometer.usecase.SetAppThemeUseCase
import com.sergiobelda.androidtodometer.usecase.SetProjectSelectedUseCase
import com.sergiobelda.androidtodometer.usecase.SetTaskDoingUseCase
import com.sergiobelda.androidtodometer.usecase.SetTaskDoneUseCase
import com.sergiobelda.androidtodometer.usecase.UpdateProjectUseCase
import com.sergiobelda.androidtodometer.usecase.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getTask: GetTaskUseCase,
    private val insertTask: InsertTaskUseCase,
    private val deleteTask: DeleteTaskUseCase,
    private val updateTask: UpdateTaskUseCase,
    private val setTaskDoing: SetTaskDoingUseCase,
    private val setTaskDone: SetTaskDoneUseCase,
    getProjects: GetProjectsUseCase,
    private val insertProject: InsertProjectUseCase,
    private val deleteProject: DeleteProjectUseCase,
    private val updateProject: UpdateProjectUseCase,
    getProjectSelected: GetProjectSelectedUseCase,
    getProjectSelectedId: GetProjectSelectedIdUseCase,
    private val setProjectSelected: SetProjectSelectedUseCase,
    getAppTheme: GetAppThemeUseCase,
    private val setAppTheme: SetAppThemeUseCase
) : ViewModel() {

    val appTheme: LiveData<Int> = getAppTheme.appTheme.asLiveData()

    val projects: LiveData<List<Project>> = getProjects().asLiveData()

    val projectSelected: LiveData<Project> = getProjectSelected().asLiveData()

    val projectSelectedId: LiveData<Int> = getProjectSelectedId.projectSelectedId.asLiveData()

    fun setProjectSelected(projectId: Int) = viewModelScope.launch {
        setProjectSelected.invoke(projectId)
    }

    fun getTask(id: Int): LiveData<Task> = getTask.invoke(id).asLiveData()

    fun insertTask(name: String, description: String, tag: Tag, taskState: TaskState) =
        viewModelScope.launch {
            insertTask.invoke(name, description, tag, taskState)
        }

    fun deleteTask(id: Int) = viewModelScope.launch {
        deleteTask.invoke(id)
    }

    fun insertProject(name: String, description: String) = viewModelScope.launch {
        insertProject.invoke(name, description)
    }

    fun deleteProject() = viewModelScope.launch {
        deleteProject.invoke()
    }

    fun setTaskDone(id: Int) = viewModelScope.launch {
        setTaskDone.invoke(id)
    }

    fun setTaskDoing(id: Int) = viewModelScope.launch {
        setTaskDoing.invoke(id)
    }

    fun updateTask(task: Task) = viewModelScope.launch {
        updateTask.invoke(task)
    }

    fun updateProject(project: Project) = viewModelScope.launch {
        updateProject.invoke(project)
    }

    fun setAppTheme(theme: Int) = viewModelScope.launch {
        setAppTheme.invoke(theme)
    }
}
