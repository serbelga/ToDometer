package com.sergiobelda.androidtodometer.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sergiobelda.androidtodometer.model.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_table ORDER BY taskId ASC")
    fun getTasks(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: Task)

    @Query("DELETE FROM task_table")
    suspend fun deleteTasks()

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM task_table WHERE taskId = :id")
    suspend fun deleteTask(id: Int)

    @Query("UPDATE task_table SET taskState = 1 WHERE taskId = :id")
    suspend fun setTaskDone(id: Int)

    @Query("UPDATE task_table SET taskState = 0 WHERE taskId = :id")
    suspend fun setTaskDoing(id: Int)
}