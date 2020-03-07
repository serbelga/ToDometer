package com.sergiobelda.androidtodometer.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sergiobelda.androidtodometer.model.Project

@Dao
interface ProjectDao {

    @Query("SELECT * FROM project_table ORDER BY id ASC")
    fun getProjects(): LiveData<List<Project>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProject(project: Project)

    @Query("DELETE FROM project_table")
    suspend fun deleteProjects()

    @Delete
    suspend fun deleteProject(project: Project)

    @Query("DELETE FROM project_table WHERE id = :id")
    suspend fun deleteProject(id: Int)
}