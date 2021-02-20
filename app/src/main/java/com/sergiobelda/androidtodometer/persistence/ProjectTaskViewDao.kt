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

package com.sergiobelda.androidtodometer.persistence

import androidx.room.Dao
import androidx.room.Query
import com.sergiobelda.androidtodometer.databaseview.ProjectTaskListing
import com.sergiobelda.androidtodometer.databaseview.ProjectTaskView
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectTaskViewDao {
    @Query("SELECT * FROM ProjectTaskView ORDER BY taskState ASC")
    fun getProjectTaskView(): Flow<List<ProjectTaskView>>

    @Query("SELECT * FROM ProjectTaskView ORDER BY taskState ASC")
    fun getTasks(): Flow<List<ProjectTaskListing>>

    @Query("SELECT * FROM ProjectTaskView WHERE projectId = :projectId ORDER BY taskState ASC")
    fun getProjectTaskListingList(projectId: Int): Flow<List<ProjectTaskListing>>

    @Query("SELECT * FROM ProjectTaskView WHERE taskId = :taskId")
    fun getProjectTaskListing(taskId: Int): Flow<ProjectTaskListing>
}
