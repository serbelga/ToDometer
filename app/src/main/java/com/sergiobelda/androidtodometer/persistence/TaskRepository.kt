package com.sergiobelda.androidtodometer.persistence

import androidx.lifecycle.LiveData
import com.sergiobelda.androidtodometer.model.Task

class TaskRepository(private val taskDao: TaskDao) {
    val tasks: LiveData<List<Task>> = taskDao.getTasks()

    suspend fun insert(task: Task) {
        taskDao.insertTask(task)
    }

    suspend fun deleteTasks() {
        taskDao.deleteTasks()
    }

    suspend fun deleteTask(id: Int) {
        taskDao.deleteTask(id)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    suspend fun setTaskDone(id: Int) {
        taskDao.setTaskDone(id)
    }

    suspend fun setTaskDoing(id: Int) {
        taskDao.setTaskDoing(id)
    }
}