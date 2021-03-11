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
import com.sergiobelda.androidtodometer.util.TestUtil
import kotlinx.coroutines.flow.firstOrNull
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
    fun testInsertTask() = runBlocking {
        val taskA = TestUtil.createTask()
        taskDao.insertTask(taskA)
        val taskB = taskDao.getTask(1).firstOrNull()
        assertThat(taskB, `is`(taskA))
    }

    @Test
    fun testGetTask() = runBlocking {
        taskDao.insertTask(
            TestUtil.createTask()
        )
        val task = taskDao.getTask(1).firstOrNull()
        assertNotNull(task)
    }

    @Test
    fun testGetTaskNotExist() = runBlocking {
        val task = taskDao.getTask(10).firstOrNull()
        assertNull(task)
    }

    @Test
    fun testGetTasks() = runBlocking {
        taskDao.insertTask(
            TestUtil.createTask()
        )
        val tasks = taskDao.getTasks().firstOrNull()
        assertFalse(tasks.isNullOrEmpty())
    }

    @Test
    @Throws(NoSuchElementException::class)
    fun testUpdateTask() = runBlocking {
        taskDao.insertTask(
            TestUtil.createTask()
        )
        var task = taskDao.getTask(1).firstOrNull()
        assertEquals("Task", task?.taskName)

        task?.taskName = "New name"
        task?.let { taskDao.updateTask(it) }

        task = taskDao.getTask(1).firstOrNull()
        assertEquals("New name", task?.taskName)
    }

    @Test
    @Throws(NoSuchElementException::class)
    fun testSetTaskStateDone() = runBlocking {
        taskDao.insertTask(
            TestUtil.createTask()
        )
        assertThat(taskDao.getTask(1).firstOrNull()?.taskState, `is`(TaskState.DOING))
        taskDao.setTaskDone(1)
        assertThat(taskDao.getTask(1).firstOrNull()?.taskState, `is`(TaskState.DONE))
    }

    @Test
    @Throws(NoSuchElementException::class)
    fun testSetTaskStateDoing() = runBlocking {
        taskDao.insertTask(
            TestUtil.createTask()
        )
        assertThat(taskDao.getTask(1).firstOrNull()?.taskState, `is`(TaskState.DOING))
        taskDao.setTaskDone(1)
        assertThat(taskDao.getTask(1).firstOrNull()?.taskState, `is`(TaskState.DONE))
        taskDao.setTaskDoing(1)
        assertThat(taskDao.getTask(1).firstOrNull()?.taskState, `is`(TaskState.DOING))
    }

    @Test
    fun testDeleteTask() = runBlocking {
        taskDao.insertTask(
            TestUtil.createTask()
        )
        assertNotNull(taskDao.getTask(1).firstOrNull())
        taskDao.deleteTask(1)
        assertNull(taskDao.getTask(1).firstOrNull())
    }
}
