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

package com.sergiobelda.androidtodometer.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.AddTaskFragmentBinding
import com.sergiobelda.androidtodometer.domain.model.Tag
import com.sergiobelda.androidtodometer.domain.model.TaskState
import com.sergiobelda.androidtodometer.extensions.hideSoftKeyboard
import com.sergiobelda.androidtodometer.ui.adapter.TagsAdapter
import com.sergiobelda.androidtodometer.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import dev.sergiobelda.android.companion.material.clearError

/**
 * A [Fragment] to create task.
 */
@AndroidEntryPoint
class AddTaskFragment : Fragment() {
    private var _binding: AddTaskFragmentBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel by viewModels<MainViewModel>()

    private var selectedTag = Tag.GRAY

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddTaskFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NavigationUI.setupWithNavController(binding.toolbar, findNavController())
        binding.toolbar.apply {
            navigationIcon =
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_round_arrow_back_24)
            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.save -> {
                        createTask()
                        true
                    }
                    else -> false
                }
            }
        }
        binding.tagList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = TagsAdapter(enumValues<Tag>().toList()).apply {
                listener = TagsAdapter.Listener {
                    selectedTag = it
                }
            }
        }
    }

    private fun createTask() {
        if (validateTaskName()) {
            insertTask()
        }
    }

    private fun validateTaskName(): Boolean {
        binding.taskNameInput.clearError()
        return if (binding.taskNameEditText.text.isNullOrBlank()) {
            binding.taskNameInput.error = getString(R.string.must_be_not_empty)
            false
        } else {
            true
        }
    }

    private fun insertTask() {
        val name = binding.taskNameEditText.text.toString()
        val description = binding.taskDescriptionEditText.text.toString()
        mainViewModel.insertTask(name, description, selectedTag, TaskState.DOING)
            .observe(viewLifecycleOwner) {
                activity?.hideSoftKeyboard()
                findNavController().navigateUp()
            }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
