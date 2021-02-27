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

package com.sergiobelda.androidtodometer.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.sergiobelda.androidtodometer.model.Tag
import com.sergiobelda.androidtodometer.model.TaskState

@Entity(
    tableName = "task_table",
    foreignKeys = [
        ForeignKey(
            entity = ProjectEntity::class,
            parentColumns = ["projectId"],
            childColumns = ["taskProjectId"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ]
)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) var taskId: Int,
    var taskName: String,
    var taskDescription: String?,
    val taskProjectId: Int?,
    var tag: Tag?,
    val taskState: TaskState
)
