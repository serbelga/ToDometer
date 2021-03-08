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

package com.sergiobelda.androidtodometer.db

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sergiobelda.androidtodometer.db.dao.ProjectDao
import com.sergiobelda.androidtodometer.util.TestUtil
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProjectDaoTest : TodometerDatabaseTest() {
    private lateinit var projectDao: ProjectDao

    @Before
    fun init() {
        projectDao = todometerDatabase.projectDao()
    }

    @Test
    @Throws(NoSuchElementException::class)
    fun testInsertProject() = runBlocking {
        val projectA = TestUtil.createProject()
        projectDao.insertProject(projectA)
        val projectB = projectDao.getProject(1).first().project
        assertThat(projectB, `is`(projectA))
    }

    @Test
    @Throws(NoSuchElementException::class)
    fun testGetProject() = runBlocking {
        projectDao.insertProject(
            TestUtil.createProject()
        )
        val project = projectDao.getProject(1).first()
        assertNotNull(project)
    }

    @Test
    @Throws(NoSuchElementException::class)
    fun testGetProjectNotExist() = runBlocking {
        val project = projectDao.getProject(10).first()
        assertNull(project)
    }

    @Test
    @Throws(NoSuchElementException::class)
    fun testGetProjects() = runBlocking {
        projectDao.insertProject(
            TestUtil.createProject()
        )
        val projects = projectDao.getProjects().first()
        assertFalse(projects.isNullOrEmpty())
    }

    @Test
    @Throws(NoSuchElementException::class)
    fun testUpdateProject() = runBlocking {
        projectDao.insertProject(
            TestUtil.createProject()
        )
        var project = projectDao.getProject(1).first()
        assertEquals("Project", project.project.projectName)

        project.project.projectName = "New name"
        projectDao.updateProject(project.project)

        project = projectDao.getProject(1).first()
        assertEquals("New name", project.project.projectName)
    }

    @Test
    @Throws(NoSuchElementException::class)
    fun testDeleteProject() = runBlocking {
        projectDao.insertProject(
            TestUtil.createProject()
        )
        var project = projectDao.getProject(1).first()
        assertNotNull(project)
        projectDao.deleteProject(1)
        project = projectDao.getProject(1).first()
        assertNull(project)
    }
}
