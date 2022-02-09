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
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.MoreBottomSheetDialogFragmentBinding
import com.sergiobelda.androidtodometer.ui.theme.appThemePreferenceOptionPairs
import dagger.hilt.android.AndroidEntryPoint
import dev.sergiobelda.android.companion.content.launchActivity
import dev.sergiobelda.android.companion.material.createMaterialDialog
import dev.sergiobelda.android.companion.material.icon
import dev.sergiobelda.android.companion.material.message
import dev.sergiobelda.android.companion.material.negativeButton
import dev.sergiobelda.android.companion.material.positiveButton
import dev.sergiobelda.android.companion.material.singleChoiceItems
import dev.sergiobelda.android.companion.material.title

/**
 * A [Fragment] showing options.
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
        initEditProjectObserver()
        initDeleteProjectObserver()
        setClickListeners()
    }

    private fun initAppThemeObserver() {
        mainViewModel.appThemePreference.observe(viewLifecycleOwner) { currentTheme ->
            appThemePreferenceOptionPairs.find { it.first == currentTheme }?.second?.let {
                binding.themeIcon.setImageResource(it.themeIconRes)
                binding.themeDescription.text = getString(it.modeNameRes)
            }
        }
    }

    private fun initDeleteProjectObserver() {
        mainViewModel.projects.observe(viewLifecycleOwner) {
            it?.let { list ->
                if (list.size > 1) {
                    enableDeleteProjectButton()
                } else {
                    disableDeleteProjectButton()
                }
            } ?: disableDeleteProjectButton()
        }
    }

    private fun enableDeleteProjectButton() {
        binding.deleteProjectIcon.isEnabled = true
        binding.deleteProjectText.isEnabled = true
        binding.deleteProject.isEnabled = true
        binding.deleteProject.setOnClickListener {
            createMaterialDialog(requireContext()) {
                icon(R.drawable.ic_warning_24dp)
                message(R.string.delete_project_dialog)
                positiveButton(getString(R.string.accept)) {
                    mainViewModel.deleteProject()
                }
                negativeButton(getString(R.string.cancel))
            }.show()
        }
    }

    private fun disableDeleteProjectButton() {
        binding.deleteProjectIcon.isEnabled = false
        binding.deleteProjectText.isEnabled = false
        binding.deleteProject.isEnabled = false
        binding.deleteProject.setOnClickListener(null)
    }

    private fun initEditProjectObserver() {
        mainViewModel.projectSelected.observe(viewLifecycleOwner) { projectSelected ->
            projectSelected?.let {
                enableEditProjectButton()
            } ?: disableEditProjectButton()
        }
    }

    private fun enableEditProjectButton() {
        binding.editProjectIcon.isEnabled = true
        binding.editProjectText.isEnabled = true
        binding.editProject.isEnabled = true
        binding.editProject.setOnClickListener {
            dismiss()
            findNavController().navigate(R.id.editProjectFragment)
        }
    }

    private fun disableEditProjectButton() {
        binding.editProjectIcon.isEnabled = false
        binding.editProjectText.isEnabled = false
        binding.editProject.isEnabled = false
        binding.editProject.setOnClickListener(null)
    }

    private fun setClickListeners() {
        binding.themeOption.setOnClickListener {
            chooseThemeClick()
        }
        binding.aboutButton.setOnClickListener {
            dismiss()
            findNavController().navigate(R.id.aboutFragment)
        }
        binding.openSourceLicensesButton.setOnClickListener {
            dismiss()
            context?.launchActivity<OssLicensesMenuActivity> {
                putExtra("title", getString(R.string.open_source_licenses))
            }
        }
    }

    private fun chooseThemeClick() {
        val currentTheme = mainViewModel.appThemePreference.value
        var checkedItem = appThemePreferenceOptionPairs.indexOfFirst { it.first == currentTheme }
        if (checkedItem >= 0) {
            val items = appThemePreferenceOptionPairs.map {
                getText(it.second.modeNameRes)
            }.toTypedArray()
            createMaterialDialog(requireContext()) {
                title(R.string.choose_theme)
                singleChoiceItems(items, checkedItem) {
                    checkedItem = it
                }
                positiveButton(getString(R.string.accept)) {
                    val pair = appThemePreferenceOptionPairs.getOrNull(checkedItem)
                    pair?.let {
                        AppCompatDelegate.setDefaultNightMode(it.second.modeNight)
                        // Update theme description TextView
                        binding.themeDescription.text = getString(it.second.modeNameRes)
                        mainViewModel.setAppThemePreference(it.first)
                    }
                }
                negativeButton(getString(R.string.cancel))
            }.show()
        }
    }
}
