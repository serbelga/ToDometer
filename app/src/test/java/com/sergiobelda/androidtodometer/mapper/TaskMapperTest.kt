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

package com.sergiobelda.androidtodometer.mapper

import com.sergiobelda.androidtodometer.db.entity.TaskEntity
import com.sergiobelda.androidtodometer.mapper.TaskMapper.toDomain
import com.sergiobelda.androidtodometer.mapper.TaskMapper.toEntity
import com.sergiobelda.androidtodometer.model.Tag
import com.sergiobelda.androidtodometer.model.Task
import com.sergiobelda.androidtodometer.model.TaskState
import org.junit.Assert.assertEquals
import org.junit.Test

class TaskMapperTest {

    @Test
    fun `Task to TaskEntity`() {
        val task = Task(1, "Name", "Description", TaskState.DOING, 2, Tag.OTHER)
        val taskEntity = task.toEntity()
        assertEquals(task.id, taskEntity?.taskId)
        assertEquals(task.name, taskEntity?.taskName)
        assertEquals(task.description, taskEntity?.taskDescription)
        assertEquals(task.taskState, taskEntity?.taskState)
        assertEquals(task.projectId, taskEntity?.taskProjectId)
        assertEquals(task.tag, taskEntity?.tag)
    }

    @Test
    fun `TaskEntity to Task`() {
        val taskEntity = TaskEntity(1, "Name", "Description", TaskState.DOING, 2, Tag.OTHER)
        val task = taskEntity.toDomain()
        assertEquals(taskEntity.taskId, task?.id)
        assertEquals(taskEntity.taskName, task?.name)
        assertEquals(taskEntity.taskDescription, task?.description)
        assertEquals(taskEntity.taskState, task?.taskState)
        assertEquals(taskEntity.taskProjectId, task?.projectId)
        assertEquals(taskEntity.tag, task?.tag)
    }
}
