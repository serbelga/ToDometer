package com.sergiobelda.androidtodometer.domain.repository

import com.sergiobelda.androidtodometer.domain.model.AppThemePreference
import kotlinx.coroutines.flow.Flow

interface IUserPreferencesRepository {

    fun projectSelected(): Flow<Int>

    suspend fun setProjectSelected(projectSelected: Int)

    fun getAppThemePreference(): Flow<AppThemePreference>

    suspend fun setAppThemePreference(theme: AppThemePreference)

    suspend fun clearPreferences()
}
