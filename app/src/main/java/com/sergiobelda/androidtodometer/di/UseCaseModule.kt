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
import com.sergiobelda.androidtodometer.repository.TaskRepository
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
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetProjectsUseCase(
        projectRepository: ProjectRepository
    ) = GetProjectsUseCase(projectRepository)

    @Provides
    @ViewModelScoped
    fun provideGetTaskUseCase(
        taskRepository: TaskRepository
    ) = GetTaskUseCase(taskRepository)

    @Provides
    @ViewModelScoped
    fun provideInsertTaskUseCase(
        userPreferencesRepository: UserPreferencesRepository,
        taskRepository: TaskRepository
    ) = InsertTaskUseCase(userPreferencesRepository, taskRepository)

    @Provides
    @ViewModelScoped
    fun provideDeleteTaskUseCase(
        taskRepository: TaskRepository
    ) = DeleteTaskUseCase(taskRepository)

    @Provides
    @ViewModelScoped
    fun provideSetTaskDoingUseCase(
        taskRepository: TaskRepository
    ) = SetTaskDoingUseCase(taskRepository)

    @Provides
    @ViewModelScoped
    fun provideSetTaskDoneUseCase(
        taskRepository: TaskRepository
    ) = SetTaskDoneUseCase(taskRepository)

    @Provides
    @ViewModelScoped
    fun provideUpdateTaskUseCase(
        taskRepository: TaskRepository
    ) = UpdateTaskUseCase(taskRepository)

    @Provides
    @ViewModelScoped
    fun provideSetProjectSelectedUseCase(
        userPreferencesRepository: UserPreferencesRepository
    ) = SetProjectSelectedUseCase(userPreferencesRepository)

    @Provides
    @ViewModelScoped
    fun provideGetProjectIdSelectedUseCase(
        userPreferencesRepository: UserPreferencesRepository
    ) = GetProjectSelectedIdUseCase(userPreferencesRepository)

    @Provides
    @ViewModelScoped
    fun provideGetProjectSelectedUseCase(
        userPreferencesRepository: UserPreferencesRepository,
        projectRepository: ProjectRepository
    ) = GetProjectSelectedUseCase(userPreferencesRepository, projectRepository)

    @Provides
    @ViewModelScoped
    fun provideInsertProjectUseCase(
        userPreferencesRepository: UserPreferencesRepository,
        projectRepository: ProjectRepository
    ) = InsertProjectUseCase(userPreferencesRepository, projectRepository)

    @Provides
    @ViewModelScoped
    fun provideDeleteProjectUseCase(
        userPreferencesRepository: UserPreferencesRepository,
        projectRepository: ProjectRepository
    ) = DeleteProjectUseCase(userPreferencesRepository, projectRepository)

    @Provides
    @ViewModelScoped
    fun provideUpdateProjectUseCase(
        projectRepository: ProjectRepository
    ) = UpdateProjectUseCase(projectRepository)

    @Provides
    @ViewModelScoped
    fun provideGetAppThemeUseCase(
        userPreferencesRepository: UserPreferencesRepository
    ) = GetAppThemeUseCase(userPreferencesRepository)

    @Provides
    @ViewModelScoped
    fun provideSetAppThemeUseCase(
        userPreferencesRepository: UserPreferencesRepository
    ) = SetAppThemeUseCase(userPreferencesRepository)
}
