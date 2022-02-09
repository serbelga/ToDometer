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

import com.sergiobelda.androidtodometer.domain.repository.IProjectRepository
import com.sergiobelda.androidtodometer.domain.repository.ITaskRepository
import com.sergiobelda.androidtodometer.domain.repository.IUserPreferencesRepository
import com.sergiobelda.androidtodometer.domain.usecase.DeleteProjectUseCase
import com.sergiobelda.androidtodometer.domain.usecase.DeleteTaskUseCase
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
        projectRepository: IProjectRepository
    ) = GetProjectsUseCase(projectRepository)

    @Provides
    @ViewModelScoped
    fun provideGetTaskUseCase(
        taskRepository: ITaskRepository
    ) = GetTaskUseCase(taskRepository)

    @Provides
    @ViewModelScoped
    fun provideInsertTaskUseCase(
        userPreferencesRepository: IUserPreferencesRepository,
        taskRepository: ITaskRepository
    ) = InsertTaskUseCase(userPreferencesRepository, taskRepository)

    @Provides
    @ViewModelScoped
    fun provideDeleteTaskUseCase(
        taskRepository: ITaskRepository
    ) = DeleteTaskUseCase(taskRepository)

    @Provides
    @ViewModelScoped
    fun provideSetTaskDoingUseCase(
        taskRepository: ITaskRepository
    ) = SetTaskDoingUseCase(taskRepository)

    @Provides
    @ViewModelScoped
    fun provideSetTaskDoneUseCase(
        taskRepository: ITaskRepository
    ) = SetTaskDoneUseCase(taskRepository)

    @Provides
    @ViewModelScoped
    fun provideUpdateTaskUseCase(
        taskRepository: ITaskRepository
    ) = UpdateTaskUseCase(taskRepository)

    @Provides
    @ViewModelScoped
    fun provideSetProjectSelectedUseCase(
        userPreferencesRepository: IUserPreferencesRepository
    ) = SetProjectSelectedUseCase(userPreferencesRepository)

    @Provides
    @ViewModelScoped
    fun provideGetProjectIdSelectedUseCase(
        userPreferencesRepository: IUserPreferencesRepository
    ) = GetProjectSelectedIdUseCase(userPreferencesRepository)

    @Provides
    @ViewModelScoped
    fun provideGetProjectSelectedUseCase(
        userPreferencesRepository: IUserPreferencesRepository,
        projectRepository: IProjectRepository
    ) = GetProjectSelectedUseCase(userPreferencesRepository, projectRepository)

    @Provides
    @ViewModelScoped
    fun provideInsertProjectUseCase(
        userPreferencesRepository: IUserPreferencesRepository,
        projectRepository: IProjectRepository
    ) = InsertProjectUseCase(userPreferencesRepository, projectRepository)

    @Provides
    @ViewModelScoped
    fun provideDeleteProjectUseCase(
        userPreferencesRepository: IUserPreferencesRepository,
        projectRepository: IProjectRepository
    ) = DeleteProjectUseCase(userPreferencesRepository, projectRepository)

    @Provides
    @ViewModelScoped
    fun provideUpdateProjectUseCase(
        projectRepository: IProjectRepository
    ) = UpdateProjectUseCase(projectRepository)

    @Provides
    @ViewModelScoped
    fun provideSetAppThemePreferenceUseCase(
        userPreferencesRepository: IUserPreferencesRepository
    ) = SetAppThemePreferenceUseCase(userPreferencesRepository)
}
