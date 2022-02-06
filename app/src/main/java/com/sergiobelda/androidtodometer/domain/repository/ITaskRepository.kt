package com.sergiobelda.androidtodometer.domain.repository

import com.sergiobelda.androidtodometer.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface ITaskRepository {

    fun getTask(id: Int): Flow<Task?>

    suspend fun deleteTask(id: Int)

    suspend fun insert(task: Task): Long?

    suspend fun setTaskDone(id: Int)

    suspend fun setTaskDoing(id: Int)

    suspend fun updateTask(task: Task)
}
