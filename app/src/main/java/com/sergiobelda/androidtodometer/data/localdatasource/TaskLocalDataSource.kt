package com.sergiobelda.androidtodometer.data.localdatasource

import com.sergiobelda.androidtodometer.data.database.dao.TaskDao
import com.sergiobelda.androidtodometer.data.database.mapper.TaskMapper.toDomain
import com.sergiobelda.androidtodometer.data.database.mapper.TaskMapper.toEntity
import com.sergiobelda.androidtodometer.domain.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskLocalDataSource(private val taskDao: TaskDao) : ITaskLocalDataSource {

    override fun getTask(id: Int): Flow<Task?> = taskDao.getTask(id).map { it.toDomain() }

    override suspend fun deleteTask(id: Int) {
        taskDao.deleteTask(id)
    }

    override suspend fun insert(task: Task): Long? =
        task.toEntity()?.let { taskDao.insertTask(it) }

    override suspend fun setTaskDone(id: Int) {
        taskDao.setTaskDone(id)
    }

    override suspend fun setTaskDoing(id: Int) {
        taskDao.setTaskDoing(id)
    }

    override suspend fun updateTask(task: Task) {
        task.toEntity()?.let { taskDao.updateTask(it) }
    }
}
