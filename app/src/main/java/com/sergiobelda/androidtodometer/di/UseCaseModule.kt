/*
 * Copyright 2021 Sergio Belda Galbis
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

package com.sergiobelda.androidtodometer.di

import com.sergiobelda.androidtodometer.preferences.UserPreferencesRepository
import com.sergiobelda.androidtodometer.repository.ProjectRepository
import com.sergiobelda.androidtodometer.repository.ProjectTaskViewRepository
import com.sergiobelda.androidtodometer.repository.TaskRepository
import com.sergiobelda.androidtodometer.usecase.DeleteProjectUseCase
import com.sergiobelda.androidtodometer.usecase.DeleteTaskUseCase
import com.sergiobelda.androidtodometer.usecase.GetAppThemeUseCase
import com.sergiobelda.androidtodometer.usecase.GetProjectSelectedIdUseCase
import com.sergiobelda.androidtodometer.usecase.GetProjectSelectedUseCase
import com.sergiobelda.androidtodometer.usecase.GetProjectsUseCase
import com.sergiobelda.androidtodometer.usecase.GetTaskUseCase
import com.sergiobelda.androidtodometer.usecase.GetTasksUseCase
import com.sergiobelda.androidtodometer.usecase.InsertProjectUseCase
import com.sergiobelda.androidtodometer.usecase.InsertTaskUseCase
import com.sergiobelda.androidtodometer.usecase.SetAppThemeUseCase
import com.sergiobelda.androidtodometer.usecase.SetProjectSelectedUseCase
import com.sergiobelda.androidtodometer.usecase.SetTaskDoingUseCase
import com.sergiobelda.androidtodometer.usecase.SetTaskDoneUseCase
import com.sergiobelda.androidtodometer.usecase.UpdateProjectUseCase
import com.sergiobelda.androidtodometer.usecase.UpdateTaskUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetTasksUseCase(
        userPreferencesRepository: UserPreferencesRepository,
        projectTaskViewRepository: ProjectTaskViewRepository
    ) = GetTasksUseCase(userPreferencesRepository, projectTaskViewRepository)

    @Provides
    fun provideGetProjectsUseCase(
        projectRepository: ProjectRepository
    ) = GetProjectsUseCase(projectRepository)

    @Provides
    fun provideGetTaskUseCase(
        taskRepository: TaskRepository
    ) = GetTaskUseCase(taskRepository)

    @Provides
    fun provideInsertTaskUseCase(
        userPreferencesRepository: UserPreferencesRepository,
        taskRepository: TaskRepository
    ) = InsertTaskUseCase(userPreferencesRepository, taskRepository)

    @Provides
    fun provideDeleteTaskUseCase(
        taskRepository: TaskRepository
    ) = DeleteTaskUseCase(taskRepository)

    @Provides
    fun provideSetTaskDoingUseCase(
        taskRepository: TaskRepository
    ) = SetTaskDoingUseCase(taskRepository)

    @Provides
    fun provideSetTaskDoneUseCase(
        taskRepository: TaskRepository
    ) = SetTaskDoneUseCase(taskRepository)

    @Provides
    fun provideUpdateTaskUseCase(
        taskRepository: TaskRepository
    ) = UpdateTaskUseCase(taskRepository)

    @Provides
    fun provideSetProjectSelectedUseCase(
        userPreferencesRepository: UserPreferencesRepository
    ) = SetProjectSelectedUseCase(userPreferencesRepository)

    @Provides
    fun provideGetProjectIdSelectedUseCase(
        userPreferencesRepository: UserPreferencesRepository
    ) = GetProjectSelectedIdUseCase(userPreferencesRepository)

    @Provides
    fun provideGetProjectSelectedUseCase(
        userPreferencesRepository: UserPreferencesRepository,
        projectRepository: ProjectRepository
    ) = GetProjectSelectedUseCase(userPreferencesRepository, projectRepository)

    @Provides
    fun provideInsertProjectUseCase(
        projectRepository: ProjectRepository
    ) = InsertProjectUseCase(projectRepository)

    @Provides
    fun provideDeleteProjectUseCase(
        userPreferencesRepository: UserPreferencesRepository,
        projectRepository: ProjectRepository
    ) = DeleteProjectUseCase(userPreferencesRepository, projectRepository)

    @Provides
    fun provideUpdateProjectUseCase(
        projectRepository: ProjectRepository
    ) = UpdateProjectUseCase(projectRepository)

    @Provides
    fun provideGetAppThemeUseCase(
        userPreferencesRepository: UserPreferencesRepository
    ) = GetAppThemeUseCase(userPreferencesRepository)

    @Provides
    fun provideSetAppThemeUseCase(
        userPreferencesRepository: UserPreferencesRepository
    ) = SetAppThemeUseCase(userPreferencesRepository)
}
