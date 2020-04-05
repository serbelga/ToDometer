package com.sergiobelda.androidtodometer.repository

import com.sergiobelda.androidtodometer.persistence.TaskDao
import com.sergiobelda.androidtodometer.model.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {
    val tasks: Flow<List<Task>> = taskDao.getTasks()

    suspend fun insert(task: Task) = taskDao.insertTask(task)

    suspend fun deleteTasks() = taskDao.deleteTasks()

    suspend fun deleteTask(id: Int) = taskDao.deleteTask(id)

    suspend fun updateTask(task: Task) = taskDao.updateTask(task)

    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)

    suspend fun setTaskDone(id: Int) = taskDao.setTaskDone(id)

    suspend fun setTaskDoing(id: Int) = taskDao.setTaskDoing(id)
}