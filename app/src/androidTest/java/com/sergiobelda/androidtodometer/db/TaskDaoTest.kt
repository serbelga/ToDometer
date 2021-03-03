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

package com.sergiobelda.androidtodometer.db

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sergiobelda.androidtodometer.db.dao.ProjectDao
import com.sergiobelda.androidtodometer.db.dao.TaskDao
import com.sergiobelda.androidtodometer.model.TaskState
import com.sergiobelda.androidtodometer.utilities.TestUtil
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TaskDaoTest : TodometerDatabaseTest() {
    private lateinit var taskDao: TaskDao

    private lateinit var projectDao: ProjectDao

    @Before
    fun init() {
        taskDao = todometerDatabase.taskDao()
        projectDao = todometerDatabase.projectDao()
        runBlocking {
            projectDao.insertProject(
                TestUtil.createProject()
            )
        }
    }

    @Test
    @Throws(NoSuchElementException::class)
    fun testInsertTask() = runBlocking {
        val taskA = TestUtil.createTask()
        taskDao.insertTask(taskA)
        val taskB = taskDao.getTask(1).first()
        assertThat(taskB, `is`(taskA))
    }

    @Test
    @Throws(NoSuchElementException::class)
    fun testGetTask() = runBlocking {
        taskDao.insertTask(
            TestUtil.createTask()
        )
        val task = taskDao.getTask(1).first()
        assertNotNull(task)
    }

    @Test
    @Throws(NoSuchElementException::class)
    fun testGetTaskNotExist() = runBlocking {
        val task = taskDao.getTask(10).first()
        assertNull(task)
    }

    @Test
    @Throws(NoSuchElementException::class)
    fun testGetTasks() = runBlocking {
        taskDao.insertTask(
            TestUtil.createTask()
        )
        val tasks = taskDao.getTasks().first()
        assertFalse(tasks.isNullOrEmpty())
    }

    @Test
    @Throws(NoSuchElementException::class)
    fun testUpdateTask() = runBlocking {
        taskDao.insertTask(
            TestUtil.createTask()
        )
        var task = taskDao.getTask(1).first()
        assertEquals("Task", task.taskName)

        task.taskName = "New name"
        taskDao.updateTask(task)

        task = taskDao.getTask(1).first()
        assertEquals("New name", task.taskName)
    }

    @Test
    @Throws(NoSuchElementException::class)
    fun testSetTaskStateDone() = runBlocking {
        taskDao.insertTask(
            TestUtil.createTask()
        )
        assertThat(taskDao.getTask(1).first().taskState, `is`(TaskState.DOING))
        taskDao.setTaskDone(1)
        assertThat(taskDao.getTask(1).first().taskState, `is`(TaskState.DONE))
    }

    @Test
    @Throws(NoSuchElementException::class)
    fun testSetTaskStateDoing() = runBlocking {
        taskDao.insertTask(
            TestUtil.createTask()
        )
        assertThat(taskDao.getTask(1).first().taskState, `is`(TaskState.DOING))
        taskDao.setTaskDone(1)
        assertThat(taskDao.getTask(1).first().taskState, `is`(TaskState.DONE))
        taskDao.setTaskDoing(1)
        assertThat(taskDao.getTask(1).first().taskState, `is`(TaskState.DOING))
    }

    @Test
    @Throws(NoSuchElementException::class)
    fun testDeleteTask() = runBlocking {
        taskDao.insertTask(
            TestUtil.createTask()
        )
        assertNotNull(taskDao.getTask(1).first())
        taskDao.deleteTask(1)
        assertNull(taskDao.getTask(1).first())
    }
}
