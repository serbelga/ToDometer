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

package com.sergiobelda.androidtodometer.preferences

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserPreferencesRepositoryTest {

    lateinit var userPreferencesRepository: UserPreferencesRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        userPreferencesRepository = UserPreferencesRepository(context)
    }

    @Test
    fun testGetProjectSelectedDefault() = runBlocking {
        assertEquals(1, userPreferencesRepository.projectSelected().firstOrNull())
    }

    @Test
    fun testSetProjectSelected() = runBlocking {
        userPreferencesRepository.setProjectSelected(2)
        assertEquals(2, userPreferencesRepository.projectSelected().firstOrNull())
    }

    @After
    fun clearPreferences() = runBlocking {
        userPreferencesRepository.clearPreferences()
    }
}
