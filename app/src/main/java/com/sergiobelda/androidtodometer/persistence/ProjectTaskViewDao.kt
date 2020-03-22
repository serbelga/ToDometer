package com.sergiobelda.androidtodometer.persistence

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.sergiobelda.androidtodometer.databaseview.ProjectTaskView
import com.sergiobelda.androidtodometer.databaseview.ProjectTaskListing

@Dao
interface ProjectTaskViewDao {
    @Query("SELECT * FROM ProjectTaskView")
    fun getProjectTaskView(): LiveData<List<ProjectTaskView>>

    @Query("SELECT * FROM ProjectTaskView ORDER BY taskState ASC")
    fun getProjectTaskListingList(): DataSource.Factory<Int, ProjectTaskListing>

    @Query("SELECT * FROM ProjectTaskView WHERE taskId = :id")
    fun getProjectTaskListing(id: Int): LiveData<ProjectTaskListing>
}