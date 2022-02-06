package com.sergiobelda.androidtodometer.data.localdatasource

import com.sergiobelda.androidtodometer.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface ITaskLocalDataSource {

    fun getTask(id: Int): Flow<Task?>

    suspend fun deleteTask(id: Int)

    suspend fun insert(task: Task): Long?

    suspend fun setTaskDone(id: Int)

    suspend fun setTaskDoing(id: Int)

    suspend fun updateTask(task: Task)
}
