/*
 * Copyright 2021 Sergio Belda Galbis
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

package com.sergiobelda.androidtodometer.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sergiobelda.androidtodometer.db.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("DELETE FROM task_table WHERE taskId = :id")
    suspend fun deleteTask(id: Int)

    @Query("SELECT * FROM task_table WHERE taskId = :id")
    fun getTask(id: Int): Flow<TaskEntity>

    @Query("SELECT * FROM task_table ORDER BY taskId ASC")
    fun getTasks(): Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: TaskEntity)

    @Query("UPDATE task_table SET taskState = 'DOING' WHERE taskId = :id")
    suspend fun setTaskDoing(id: Int)

    @Query("UPDATE task_table SET taskState = 'DONE' WHERE taskId = :id")
    suspend fun setTaskDone(id: Int)

    @Update
    suspend fun updateTask(task: TaskEntity)
}
