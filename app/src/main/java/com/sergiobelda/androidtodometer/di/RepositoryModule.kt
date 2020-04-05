package com.sergiobelda.androidtodometer.di

import com.sergiobelda.androidtodometer.repository.ProjectRepository
import com.sergiobelda.androidtodometer.repository.ProjectTaskViewRepository
import com.sergiobelda.androidtodometer.repository.TaskRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { TaskRepository(get()) }
    single { ProjectRepository(get()) }
    single { ProjectTaskViewRepository(get()) }
}