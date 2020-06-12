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

package com.sergiobelda.androidtodometer.databaseview

import androidx.room.DatabaseView
import androidx.room.Embedded
import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.model.Task

@DatabaseView(
    "SELECT project_table.*, task_table.* FROM project_table INNER JOIN task_table ON project_table.projectId = task_table.taskProjectId"
)
data class ProjectTaskView(
    @Embedded
    val project: Project,
    @Embedded
    val task: Task
)

data class ProjectTaskListing(
    val projectId: Int,
    val projectName: String,
    @Embedded
    val task: Task
)
