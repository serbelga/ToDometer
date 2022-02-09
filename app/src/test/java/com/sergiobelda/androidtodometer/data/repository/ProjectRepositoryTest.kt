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

package com.sergiobelda.androidtodometer.data.repository

import com.sergiobelda.androidtodometer.data.localdatasource.IProjectLocalDataSource
import com.sergiobelda.androidtodometer.domain.model.Project
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class ProjectRepositoryTest {

    @MockK
    private val projectLocalDataSource = mockk<IProjectLocalDataSource>()

    private val projectRepository = ProjectRepository(projectLocalDataSource)

    @Test
    fun testGetProjects() = runBlocking {
        val projects = listOf(Project(1, "Name", "Description"))

        coEvery { projectLocalDataSource.getProjects() } returns flow {
            emit(projects)
        }

        assertEquals(projects, projectRepository.getProjects().firstOrNull())
    }

    @Test
    fun testGetProject() = runBlocking {
        val project = Project(1, "Name", "Description")

        coEvery { projectLocalDataSource.getProject(1) } returns flow {
            emit(project)
        }

        assertEquals(project, projectRepository.getProject(1).firstOrNull())
    }
}
