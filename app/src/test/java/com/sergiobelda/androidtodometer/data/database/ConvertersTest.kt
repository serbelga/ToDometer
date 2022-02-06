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

package com.sergiobelda.androidtodometer.data.database

import com.sergiobelda.androidtodometer.domain.model.Tag
import com.sergiobelda.androidtodometer.domain.model.TaskState
import org.junit.Assert.assertEquals
import org.junit.Test

class ConvertersTest {

    @Test
    fun `Tag to String`() {
        assertEquals(Tag.RED.name, Converters().toString(Tag.RED))
    }

    @Test
    fun `String to Tag`() {
        assertEquals(Tag.RED, Converters().toTag(Tag.RED.name))
    }

    @Test
    fun `String null returns default OTHER Tag`() {
        assertEquals(Tag.GRAY, Converters().toTag(null))
    }

    @Test
    fun `TaskState to String`() {
        assertEquals(TaskState.DONE.name, Converters().toString(TaskState.DONE))
    }

    @Test
    fun `String to TaskState`() {
        assertEquals(TaskState.DOING, Converters().toTaskState(TaskState.DOING.name))
    }

    @Test
    fun `String null returns default TaskState DOING`() {
        assertEquals(TaskState.DOING, Converters().toTaskState(null))
    }
}
