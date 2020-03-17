package com.sergiobelda.androidtodometer.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.model.ProjectTask
import com.sergiobelda.androidtodometer.databaseview.ProjectTaskFull
import com.sergiobelda.androidtodometer.databaseview.ProjectTaskListing

@Dao
interface ProjectDao {

    @Query("SELECT * FROM project_table ORDER BY projectId ASC")
    fun getProjects(): LiveData<List<Project>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProject(project: Project)

    @Query("DELETE FROM project_table")
    suspend fun deleteProjects()

    @Delete
    suspend fun deleteProject(project: Project)

    @Query("DELETE FROM project_table WHERE projectId = :id")
    suspend fun deleteProject(id: Int)

    @Transaction
    @Query("SELECT * FROM project_table")
    fun getTaskProjects(): LiveData<List<ProjectTask>>

    @Query("SELECT * FROM ProjectTaskFull")
    fun getProjectTaskFull(): LiveData<List<ProjectTaskFull>>

    @Query("SELECT * FROM ProjectTaskFull")
    fun getProjectTaskListing(): LiveData<List<ProjectTaskListing>>
}