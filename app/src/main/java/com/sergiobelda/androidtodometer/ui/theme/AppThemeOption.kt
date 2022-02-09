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

package com.sergiobelda.androidtodometer.ui.theme

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatDelegate
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.domain.model.AppThemePreference

enum class AppThemeOption(
    val modeNight: Int,
    @DrawableRes val themeIconRes: Int,
    @StringRes val modeNameRes: Int
) {
    FOLLOW_SYSTEM(
        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM,
        R.drawable.ic_baseline_default_theme_24,
        R.string.follow_system
    ),
    DARK(
        AppCompatDelegate.MODE_NIGHT_YES,
        R.drawable.ic_baseline_dark_theme_24,
        R.string.dark_theme
    ),
    LIGHT(
        AppCompatDelegate.MODE_NIGHT_NO,
        R.drawable.ic_baseline_light_theme_24,
        R.string.light_theme
    )
}

val appThemePreferenceOptionPairs = arrayListOf(
    AppThemePreference.FOLLOW_SYSTEM to AppThemeOption.FOLLOW_SYSTEM,
    AppThemePreference.DARK to AppThemeOption.DARK,
    AppThemePreference.LIGHT to AppThemeOption.LIGHT
)
