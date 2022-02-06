/*
 * Copyright 2020 Sergio Belda
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sergiobelda.androidtodometer.data.repository

import com.sergiobelda.androidtodometer.data.localdatasource.ITaskLocalDataSource
import com.sergiobelda.androidtodometer.domain.model.Task
import com.sergiobelda.androidtodometer.domain.repository.ITaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepository(
    private val taskLocalDataSource: ITaskLocalDataSource
) : ITaskRepository {

    override fun getTask(id: Int): Flow<Task?> = taskLocalDataSource.getTask(id)

    override suspend fun deleteTask(id: Int) {
        taskLocalDataSource.deleteTask(id)
    }

    override suspend fun insert(task: Task): Long? = taskLocalDataSource.insert(task)

    override suspend fun setTaskDone(id: Int) {
        taskLocalDataSource.setTaskDone(id)
    }

    override suspend fun setTaskDoing(id: Int) {
        taskLocalDataSource.setTaskDoing(id)
    }

    override suspend fun updateTask(task: Task) {
        taskLocalDataSource.updateTask(task)
    }
}
