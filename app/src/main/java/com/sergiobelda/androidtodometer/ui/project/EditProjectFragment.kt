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

package com.sergiobelda.androidtodometer.ui.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionManager
import com.google.android.material.transition.MaterialFade
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.EditProjectFragmentBinding
import com.sergiobelda.androidtodometer.extensions.clearError
import com.sergiobelda.androidtodometer.extensions.hideSoftKeyboard
import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A [Fragment] to edit a Project.
 */
@AndroidEntryPoint
class EditProjectFragment : Fragment() {
    private lateinit var binding: EditProjectFragmentBinding

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.edit_project_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editProjectButton.apply {
            postDelayed(
                {
                    val transition = MaterialFade().apply {
                        duration = resources.getInteger(R.integer.fade_transition_duration).toLong()
                    }
                    TransitionManager.beginDelayedTransition(
                        requireActivity().findViewById(android.R.id.content),
                        transition
                    )
                    visibility = View.VISIBLE
                },
                200
            )
            setOnClickListener {
                if (validateProjectName()) {
                    editProject()
                }
            }
        }
        mainViewModel.projectSelected.observe(
            viewLifecycleOwner,
            {
                binding.project = it
            }
        )
    }

    private fun validateProjectName(): Boolean {
        binding.projectNameInput.clearError()
        return if (binding.projectNameEditText.text.isNullOrBlank()) {
            binding.projectNameInput.error = getString(R.string.must_be_not_empty)
            false
        } else true
    }

    private fun editProject() {
        mainViewModel.projectSelected.value?.let {
            mainViewModel.updateProject(
                Project(
                    it.id,
                    binding.projectNameEditText.text.toString(),
                    binding.projectDescriptionEditText.text.toString()
                )
            )
            activity?.hideSoftKeyboard()
            findNavController().navigateUp()
        }
    }
}
