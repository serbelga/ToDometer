package com.sergiobelda.androidtodometer.domain.repository

import com.sergiobelda.androidtodometer.domain.model.Project
import kotlinx.coroutines.flow.Flow

interface IProjectRepository {

    fun getProjects(): Flow<List<Project?>>

    fun getProject(id: Int): Flow<Project?>

    suspend fun deleteProject(id: Int)

    suspend fun insert(project: Project): Long?

    suspend fun updateProject(project: Project)
}
