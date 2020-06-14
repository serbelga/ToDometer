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

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.ChooseThemeLayoutBinding
import com.sergiobelda.androidtodometer.databinding.MoreBottomSheetDialogFragmentBinding
import com.sergiobelda.androidtodometer.preferences.Preferences.THEME_PREFERENCE
import com.sergiobelda.androidtodometer.util.MaterialDialog
import com.sergiobelda.androidtodometer.util.MaterialDialog.Companion.positiveButton

/**
 * [Fragment] showing options.
 */
class MoreBottomSheetDialogFragment : BottomSheetDialogFragment() {
    private var _binding: MoreBottomSheetDialogFragmentBinding? = null
    private val binding get() = _binding!!

    private val themeDescription = MutableLiveData<Int>()

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
        setClickListeners()

        themeDescription.observe(
            viewLifecycleOwner,
            Observer {
                binding.themeDescription.text = getString(it)
            }
        )
        val theme = getSharedPreferencesTheme()
        themeDescription.value = THEME_PREFERENCE[theme]
    }

    private fun setClickListeners() {
        binding.themeOption.setOnClickListener {
            showThemeChooserDialog()
        }
    }

    private fun getSharedPreferencesTheme(): Int {
        val sharedPref = activity?.getSharedPreferences(getString(R.string.shared_preferences_key), Context.MODE_PRIVATE)
        return sharedPref?.getInt(getString(R.string.theme), AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM) ?: AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    }

    private fun updateSharedPreferencesTheme(theme: Int) {
        val sharedPref = activity?.getSharedPreferences(getString(R.string.shared_preferences_key), Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putInt(getString(R.string.theme), theme)
            commit()
        }
    }

    private fun showThemeChooserDialog() {
        val dialogBinding = ChooseThemeLayoutBinding.inflate(layoutInflater)
        var theme = getSharedPreferencesTheme()
        when (theme) {
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> {
                dialogBinding.systemDefaultRadioButton.isChecked = true
            }
            AppCompatDelegate.MODE_NIGHT_YES -> {
                dialogBinding.darkRadioButton.isChecked = true
            }
            AppCompatDelegate.MODE_NIGHT_NO -> {
                dialogBinding.lightRadioButton.isChecked = true
            }
        }
        dialogBinding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            theme = when (checkedId) {
                dialogBinding.systemDefaultRadioButton.id -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                dialogBinding.lightRadioButton.id -> AppCompatDelegate.MODE_NIGHT_NO
                dialogBinding.darkRadioButton.id -> AppCompatDelegate.MODE_NIGHT_YES
                else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }
        }
        MaterialDialog.createDialog(requireContext()) {
            setTitle(getString(R.string.choose_theme))
            setView(dialogBinding.root)
            positiveButton(getString(R.string.ok)) {
                updateSharedPreferencesTheme(theme)
                refreshAppTheme()
            }
        }.show()
    }

    private fun refreshAppTheme() {
        val theme = getSharedPreferencesTheme()
        AppCompatDelegate.setDefaultNightMode(theme)
        themeDescription.value = THEME_PREFERENCE[theme]
    }
}
