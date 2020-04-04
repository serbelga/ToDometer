package com.sergiobelda.androidtodometer.persistence

import androidx.paging.DataSource
import androidx.room.*
import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.model.ProjectTask
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {

    @Query("SELECT * FROM project_table WHERE projectId = :id")
    fun getProject(id: Int): Flow<Project>

    @Query("SELECT * FROM project_table ORDER BY projectId ASC")
    fun getProjects(): DataSource.Factory<Int, Project>

    @Transaction
    @Query("SELECT * FROM project_table")
    fun getTaskProjects(): Flow<List<ProjectTask>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProject(project: Project)

    @Query("DELETE FROM project_table")
    suspend fun deleteProjects()

    @Delete
    suspend fun deleteProject(project: Project)

    @Query("DELETE FROM project_table WHERE projectId = :id")
    suspend fun deleteProject(id: Int)

    @Update
    suspend fun updateProject(project: Project)
}