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
class ProjectDaoTest : TodometerDatabaseTest() {
    private lateinit var projectDao: ProjectDao

    @Before
    fun init() {
        projectDao = todometerDatabase.projectDao()
    }

    @Test
    fun testInsertProject() = runBlocking {
        val projectA = TestUtil.createProject()
        projectDao.insertProject(projectA)
        val projectB = projectDao.getProject(1).firstOrNull()?.project
        assertThat(projectB, `is`(projectA))
    }

    @Test
    fun testGetProject() = runBlocking {
        projectDao.insertProject(
            TestUtil.createProject()
        )
        val project = projectDao.getProject(1).firstOrNull()
        assertNotNull(project)
    }

    @Test
    fun testGetProjectNotExist() = runBlocking {
        val project = projectDao.getProject(10).firstOrNull()
        assertNull(project)
    }

    @Test
    fun testGetProjects() = runBlocking {
        projectDao.insertProject(
            TestUtil.createProject()
        )
        val projects = projectDao.getProjects().firstOrNull()
        assertFalse(projects.isNullOrEmpty())
    }

    @Test
    fun testUpdateProject() = runBlocking {
        projectDao.insertProject(
            TestUtil.createProject()
        )
        var project = projectDao.getProject(1).firstOrNull()
        assertEquals("Project", project?.project?.projectName)

        project?.project?.projectName = "New name"
        project?.project?.let { projectDao.updateProject(it) }

        project = projectDao.getProject(1).firstOrNull()
        assertEquals("New name", project?.project?.projectName)
    }

    @Test
    fun testDeleteProject() = runBlocking {
        projectDao.insertProject(
            TestUtil.createProject()
        )
        var project = projectDao.getProject(1).firstOrNull()
        assertNotNull(project)
        projectDao.deleteProject(1)
        project = projectDao.getProject(1).firstOrNull()
        assertNull(project)
    }
}
