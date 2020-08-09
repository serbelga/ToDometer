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

package com.sergiobelda.androidtodometer.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.MoreBottomSheetDialogFragmentBinding
import com.sergiobelda.androidtodometer.preferences.PreferenceManager
import com.sergiobelda.androidtodometer.preferences.Preferences.THEME_ARRAY
import com.sergiobelda.androidtodometer.util.MaterialDialog
import com.sergiobelda.androidtodometer.util.MaterialDialog.Companion.negativeButton
import com.sergiobelda.androidtodometer.util.MaterialDialog.Companion.positiveButton
import com.sergiobelda.androidtodometer.util.MaterialDialog.Companion.singleChoiceItems
import com.sergiobelda.androidtodometer.util.MaterialDialog.Companion.title
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * [Fragment] showing options.
 */
@AndroidEntryPoint
class MoreBottomSheetDialogFragment : BottomSheetDialogFragment() {
    private var _binding: MoreBottomSheetDialogFragmentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var preferenceManager: PreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MoreBottomSheetDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setThemeName()
        setClickListeners()
    }

    /**
     * Set the name of current applied theme in Choose Theme option.
     */
    private fun setThemeName() {
        val currentTheme = preferenceManager.getUserTheme()
        var appTheme = THEME_ARRAY.first { it.modeNight == currentTheme }
        binding.themeDescription.text = getString(appTheme.modeNameRes)
    }

    private fun setClickListeners() {
        binding.themeOption.setOnClickListener {
            chooseThemeClick()
        }
    }

    private fun chooseThemeClick() {
        val items = THEME_ARRAY.map {
            getString(it.modeNameRes) as CharSequence
        }.toTypedArray()
        val currentTheme = preferenceManager.getUserTheme()
        var checkedItem = THEME_ARRAY.indexOfFirst { it.modeNight == currentTheme }
        MaterialDialog.createDialog(requireContext()) {
            title(R.string.choose_theme)
            singleChoiceItems(items, checkedItem) {
                checkedItem = it
            }
            positiveButton(getString(R.string.ok)) {
                val mode = THEME_ARRAY[checkedItem].modeNight
                AppCompatDelegate.setDefaultNightMode(mode)
                preferenceManager.setUserTheme(mode)
                // Update theme description TextView
                binding.themeDescription.text = getString(THEME_ARRAY[checkedItem].modeNameRes)
            }
            negativeButton(getString(R.string.cancel))
        }.show()
    }
}
