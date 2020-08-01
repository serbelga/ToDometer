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
import com.sergiobelda.androidtodometer.persistence.ProjectDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProjectDaoTest : AppDatabaseTest() {
    private lateinit var projectDao: ProjectDao

    @Before
    fun init() {
        projectDao = todometerDatabase.projectDao()
    }

    @Test
    fun insertProject_getProject() = runBlocking {
        projectDao.insertProject(project1)
        val project = projectDao.getProject(1).first()
        assertThat(project, `is`(project1))
    }
}
