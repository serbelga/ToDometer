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

package com.sergiobelda.androidtodometer.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.sergiobelda.androidtodometer.domain.model.AppThemePreference
import com.sergiobelda.androidtodometer.domain.model.Project
import com.sergiobelda.androidtodometer.domain.model.Tag
import com.sergiobelda.androidtodometer.domain.model.Task
import com.sergiobelda.androidtodometer.domain.model.TaskState
import com.sergiobelda.androidtodometer.domain.usecase.DeleteProjectUseCase
import com.sergiobelda.androidtodometer.domain.usecase.DeleteTaskUseCase
import com.sergiobelda.androidtodometer.domain.usecase.GetAppThemePreferenceUseCase
import com.sergiobelda.androidtodometer.domain.usecase.GetProjectSelectedIdUseCase
import com.sergiobelda.androidtodometer.domain.usecase.GetProjectSelectedUseCase
import com.sergiobelda.androidtodometer.domain.usecase.GetProjectsUseCase
import com.sergiobelda.androidtodometer.domain.usecase.GetTaskUseCase
import com.sergiobelda.androidtodometer.domain.usecase.InsertProjectUseCase
import com.sergiobelda.androidtodometer.domain.usecase.InsertTaskUseCase
import com.sergiobelda.androidtodometer.domain.usecase.SetAppThemePreferenceUseCase
import com.sergiobelda.androidtodometer.domain.usecase.SetProjectSelectedUseCase
import com.sergiobelda.androidtodometer.domain.usecase.SetTaskDoingUseCase
import com.sergiobelda.androidtodometer.domain.usecase.SetTaskDoneUseCase
import com.sergiobelda.androidtodometer.domain.usecase.UpdateProjectUseCase
import com.sergiobelda.androidtodometer.domain.usecase.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
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
    getAppThemePreference: GetAppThemePreferenceUseCase,
    private val setAppThemePreference: SetAppThemePreferenceUseCase
) : ViewModel() {

    val appThemePreference: LiveData<AppThemePreference> =
        getAppThemePreference.appThemePreference.asLiveData()

    val projects: LiveData<List<Project?>> = getProjects().asLiveData()

    val projectSelected: LiveData<Project?> = getProjectSelected().asLiveData()

    val projectSelectedId: LiveData<Int> = getProjectSelectedId.projectSelectedId.asLiveData()

    fun setProjectSelected(projectId: Int) = viewModelScope.launch {
        setProjectSelected.invoke(projectId)
    }

    fun getTask(id: Int): LiveData<Task?> = getTask.invoke(id).asLiveData()

    fun insertTask(name: String, description: String, tag: Tag, taskState: TaskState) =
        liveData {
            emit(insertTask.invoke(name, description, tag, taskState))
        }

    fun deleteTask(id: Int) = viewModelScope.launch {
        deleteTask.invoke(id)
    }

    fun insertProject(name: String, description: String) = liveData {
        emit(insertProject.invoke(name, description))
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

    fun setAppThemePreference(theme: AppThemePreference) = viewModelScope.launch {
        setAppThemePreference.invoke(theme)
    }
}
