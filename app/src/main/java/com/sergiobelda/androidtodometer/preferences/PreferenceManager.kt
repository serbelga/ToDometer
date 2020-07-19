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

package com.sergiobelda.androidtodometer.preferences

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit

class PreferenceManager(
    private val sharedPreferences: SharedPreferences
) {
    companion object {
        const val USER_THEME = "user_theme"
    }

    fun setUserTheme(theme: Int) {
        sharedPreferences.edit {
            putInt(USER_THEME, theme)
        }
    }

    fun getUserTheme(): Int {
        return sharedPreferences.getInt(USER_THEME, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }

    fun removeUserTheme() {
        sharedPreferences.edit {
            remove(USER_THEME)
        }
    }
}
