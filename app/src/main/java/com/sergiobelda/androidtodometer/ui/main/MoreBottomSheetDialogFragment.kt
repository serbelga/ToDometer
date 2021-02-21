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

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.MoreBottomSheetDialogFragmentBinding
import com.sergiobelda.androidtodometer.preferences.AppTheme.Companion.THEME_ARRAY
import com.sergiobelda.androidtodometer.util.MaterialDialog
import com.sergiobelda.androidtodometer.util.MaterialDialog.Companion.icon
import com.sergiobelda.androidtodometer.util.MaterialDialog.Companion.message
import com.sergiobelda.androidtodometer.util.MaterialDialog.Companion.negativeButton
import com.sergiobelda.androidtodometer.util.MaterialDialog.Companion.positiveButton
import com.sergiobelda.androidtodometer.util.MaterialDialog.Companion.singleChoiceItems
import com.sergiobelda.androidtodometer.util.MaterialDialog.Companion.title
import com.sergiobelda.androidtodometer.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * [Fragment] showing options.
 */
@AndroidEntryPoint
class MoreBottomSheetDialogFragment : BottomSheetDialogFragment() {
    private var _binding: MoreBottomSheetDialogFragmentBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MoreBottomSheetDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAppThemeObserver()
        initDeleteProjectObserver()
        setClickListeners()
    }

    private fun initAppThemeObserver() {
        mainViewModel.appTheme.observe(
            viewLifecycleOwner,
            { currentTheme ->
                val appTheme = THEME_ARRAY.firstOrNull() { it.modeNight == currentTheme }
                appTheme?.let {
                    binding.themeIcon.setImageResource(it.themeIconRes)
                    binding.themeDescription.text = getString(it.modeNameRes)
                }
            }
        )
    }

    private fun initDeleteProjectObserver() {
        mainViewModel.projects.observe(
            viewLifecycleOwner,
            {
                it?.let { list ->
                    if (list.size > 1) {
                        binding.deleteProjectIcon.isEnabled = true
                        binding.deleteProjectText.isEnabled = true
                        binding.deleteProject.isEnabled = true
                        binding.deleteProject.setOnClickListener {
                            MaterialDialog.createDialog(requireContext()) {
                                icon(R.drawable.ic_warning_24dp)
                                message(R.string.delete_project_dialog)
                                positiveButton(getString(R.string.ok)) {
                                    mainViewModel.deleteProject()
                                }
                                negativeButton(getString(R.string.cancel))
                            }.show()
                        }
                    } else {
                        binding.deleteProjectIcon.isEnabled = false
                        binding.deleteProjectText.isEnabled = false
                        binding.deleteProject.isEnabled = false
                        binding.deleteProject.setOnClickListener(null)
                    }
                }
            }
        )
    }

    private fun setClickListeners() {
        binding.editProject.setOnClickListener {
            dismiss()
            findNavController().navigate(R.id.editProjectFragment)
        }
        binding.themeOption.setOnClickListener {
            chooseThemeClick()
        }
        binding.aboutButton.setOnClickListener {
            dismiss()
            findNavController().navigate(R.id.aboutFragment)
        }
        binding.openSourceLicensesButton.setOnClickListener {
            dismiss()
            val intent = Intent(requireContext(), OssLicensesMenuActivity::class.java)
            intent.putExtra("title", getString(R.string.open_source_licenses))
            startActivity(intent)
        }
    }

    private fun chooseThemeClick() {
        val currentTheme = mainViewModel.appTheme.value
        var checkedItem = THEME_ARRAY.indexOfFirst { it.modeNight == currentTheme }
        if (checkedItem >= 0) {
            val items = THEME_ARRAY.map {
                getText(it.modeNameRes)
            }.toTypedArray()
            MaterialDialog.createDialog(requireContext()) {
                title(R.string.choose_theme)
                singleChoiceItems(items, checkedItem) {
                    checkedItem = it
                }
                positiveButton(getString(R.string.ok)) {
                    val mode = THEME_ARRAY[checkedItem].modeNight
                    AppCompatDelegate.setDefaultNightMode(mode)
                    mainViewModel.setAppTheme(mode)
                    // Update theme description TextView
                    binding.themeDescription.text = getString(THEME_ARRAY[checkedItem].modeNameRes)
                }
                negativeButton(getString(R.string.cancel))
            }.show()
        }
    }
}
