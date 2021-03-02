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
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionManager
import com.google.android.material.transition.MaterialFade
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.AddProjectFragmentBinding
import com.sergiobelda.androidtodometer.extensions.clearError
import com.sergiobelda.androidtodometer.extensions.hideSoftKeyboard
import com.sergiobelda.androidtodometer.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A [Fragment] to create project.
 */
@AndroidEntryPoint
class AddProjectFragment : Fragment() {
    private var _binding: AddProjectFragmentBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddProjectFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.createButton.apply {
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
                resources.getInteger(R.integer.fade_transition_start_delay).toLong()
            )
            setOnClickListener {
                if (validateProjectName()) {
                    insertProject()
                }
            }
        }
    }

    private fun validateProjectName(): Boolean {
        binding.projectNameInput.clearError()
        return if (binding.projectNameEditText.text.isNullOrBlank()) {
            binding.projectNameInput.error = getString(R.string.must_be_not_empty)
            false
        } else true
    }

    private fun insertProject() {
        val name = binding.projectNameEditText.text.toString()
        val description = binding.projectDescriptionEditText.text.toString()
        mainViewModel.insertProject(name, description)
        activity?.hideSoftKeyboard()
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
