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

package com.sergiobelda.androidtodometer.data.database.mapper

import com.sergiobelda.androidtodometer.data.database.entity.TaskEntity
import com.sergiobelda.androidtodometer.data.database.mapper.TaskMapper.toDomain
import com.sergiobelda.androidtodometer.data.database.mapper.TaskMapper.toEntity
import com.sergiobelda.androidtodometer.domain.model.Tag
import com.sergiobelda.androidtodometer.domain.model.Task
import com.sergiobelda.androidtodometer.domain.model.TaskState
import org.junit.Assert.assertEquals
import org.junit.Test

class TaskMapperTest {

    @Test
    fun `Task to TaskEntity`() {
        val task = Task(1, "Name", "Description", TaskState.DOING, 2, Tag.GRAY)
        val taskEntity = task.toEntity()
        assertEquals(task.id, taskEntity?.id)
        assertEquals(task.name, taskEntity?.name)
        assertEquals(task.description, taskEntity?.description)
        assertEquals(task.state, taskEntity?.state)
        assertEquals(task.projectId, taskEntity?.projectId)
        assertEquals(task.tag, taskEntity?.tag)
    }

    @Test
    fun `TaskEntity to Task`() {
        val taskEntity = TaskEntity(1, "Name", "Description", TaskState.DOING, 2, Tag.GRAY)
        val task = taskEntity.toDomain()
        assertEquals(taskEntity.id, task?.id)
        assertEquals(taskEntity.name, task?.name)
        assertEquals(taskEntity.description, task?.description)
        assertEquals(taskEntity.state, task?.state)
        assertEquals(taskEntity.projectId, task?.projectId)
        assertEquals(taskEntity.tag, task?.tag)
    }
}
