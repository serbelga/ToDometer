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

package com.sergiobelda.androidtodometer.db.view

import androidx.room.DatabaseView
import androidx.room.Embedded
import com.sergiobelda.androidtodometer.db.entity.TaskEntity

@DatabaseView(
    "SELECT " +
            "t.*, p.projectName as projectName " +
            "FROM task_table t LEFT JOIN project_table p ON t.taskProjectId = p.projectId " +
            "ORDER BY projectId"
)
data class TaskProjectView(
    @Embedded val task: TaskEntity,
    val projectName: String?
)
