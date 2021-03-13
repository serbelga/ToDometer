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

package com.sergiobelda.androidtodometer.usecase

import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.preferences.UserPreferencesRepository
import com.sergiobelda.androidtodometer.repository.ProjectRepository
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetProjectSelectedUseCaseTest {

    @MockK
    private val userPreferencesRepository = mockk<UserPreferencesRepository>()

    @MockK
    private val projectRepository = mockk<ProjectRepository>()

    private val getProjectSelectedUseCase =
        GetProjectSelectedUseCase(userPreferencesRepository, projectRepository)

    @Test
    fun testGetProjectSelectedUseCase() = runBlocking {
        val project = Project(1, "Name", "Description")

        coEvery { userPreferencesRepository.projectSelected() } returns flow {
            emit(1)
        }
        coEvery { projectRepository.getProject(1) } returns flow {
            emit(project)
        }

        assertEquals(project, getProjectSelectedUseCase().firstOrNull())
    }
}