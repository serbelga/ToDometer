package com.sergiobelda.androidtodometer.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.sergiobelda.androidtodometer.model.TaskProject

@Dao
interface TaskProjectDao {

    @Transaction
    @Query("SELECT * FROM project_table")
    fun getTaskProjects(): LiveData<List<TaskProject>>
}