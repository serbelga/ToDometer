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

package com.sergiobelda.androidtodometer.ui.util

import com.sergiobelda.androidtodometer.domain.model.Tag
import com.sergiobelda.androidtodometer.domain.model.Task
import com.sergiobelda.androidtodometer.domain.model.TaskState
import com.sergiobelda.androidtodometer.ui.util.ProgressUtil.getTasksDoneProgress
import org.junit.Assert.assertEquals
import org.junit.Test

class ProgressUtilTest {

    @Test
    fun `Test getTasksDoneProgress`() {
        val tasks = arrayListOf<Task>()
        assertEquals(0, getTasksDoneProgress(tasks))
        tasks.add(Task(1, "", "", TaskState.DONE, 2, Tag.GRAY))
        assertEquals(100, getTasksDoneProgress(tasks))
        tasks.add(Task(2, "", "", TaskState.DOING, 2, Tag.GRAY))
        assertEquals(50, getTasksDoneProgress(tasks))
        tasks.add(
            Task(2, "", "", TaskState.DOING, 2, Tag.GRAY)
        )
        assertEquals(33, getTasksDoneProgress(tasks))
    }

    @Test
    fun `getPercentage for a progress value between 0 and 100`() {
        var percentage = ProgressUtil.getPercentage(50)
        assertEquals("50%", percentage)
        percentage = ProgressUtil.getPercentage(0)
        assertEquals("0%", percentage)
        percentage = ProgressUtil.getPercentage(100)
        assertEquals("100%", percentage)
    }

    @Test
    fun `getPercentage for a progress value less than 0`() {
        val percentage = ProgressUtil.getPercentage(-1)
        assertEquals("-%", percentage)
    }

    @Test
    fun `getPercentage for a progress value greater than 100`() {
        val percentage = ProgressUtil.getPercentage(105)
        assertEquals("-%", percentage)
    }
}
