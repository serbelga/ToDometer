package com.sergiobelda.androidtodometer.domain.repository

import kotlinx.coroutines.flow.Flow

interface IUserPreferencesRepository {

    fun projectSelected(): Flow<Int>

    suspend fun setProjectSelected(projectSelected: Int)

    fun getUserTheme(): Flow<Int>

    suspend fun setUserTheme(theme: Int)

    suspend fun clearPreferences()
}
