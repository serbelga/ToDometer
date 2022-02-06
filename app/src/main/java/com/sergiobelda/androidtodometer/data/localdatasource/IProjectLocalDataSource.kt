package com.sergiobelda.androidtodometer.data.localdatasource

import com.sergiobelda.androidtodometer.domain.model.Project
import kotlinx.coroutines.flow.Flow

interface IProjectLocalDataSource {

    fun getProjects(): Flow<List<Project?>>

    fun getProject(id: Int): Flow<Project?>

    suspend fun deleteProject(id: Int)

    suspend fun insert(project: Project): Long?

    suspend fun updateProject(project: Project)
}
