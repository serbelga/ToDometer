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

package com.sergiobelda.androidtodometer.domain.usecase

import com.sergiobelda.androidtodometer.domain.model.Project
import com.sergiobelda.androidtodometer.domain.repository.IProjectRepository
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetProjectsUseCaseTest {

    @MockK
    private val projectRepository = mockk<IProjectRepository>()

    private val getProjectsUseCase = GetProjectsUseCase(projectRepository)

    @Test
    fun testGetProjectsUseCase() = runBlocking {
        val projects = listOf(Project(1, "Name", "Description"))

        coEvery { projectRepository.getProjects() } returns flow {
            emit(projects)
        }

        assertEquals(projects, getProjectsUseCase().firstOrNull())
    }
}
