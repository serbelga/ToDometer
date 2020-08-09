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
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.sergiobelda.android_companion.hideSoftKeyboard
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.AddTaskFragmentBinding
import com.sergiobelda.androidtodometer.model.Tag
import com.sergiobelda.androidtodometer.model.Task
import com.sergiobelda.androidtodometer.model.TaskState
import com.sergiobelda.androidtodometer.ui.adapter.TagAdapter
import com.sergiobelda.androidtodometer.ui.main.MainActivity
import com.sergiobelda.androidtodometer.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A [Fragment] to create task.
 */
@AndroidEntryPoint
class AddTaskFragment : Fragment() {
    private var _binding: AddTaskFragmentBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel by viewModels<MainViewModel>()

    private var projectId = 0
    private var tag = Tag.OTHER

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddTaskFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.createButton.setOnClickListener {
            insertTask()
        }
        mainViewModel.projects.observe(
            viewLifecycleOwner,
            Observer {
                val adapter = ArrayAdapter(
                    requireContext(),
                    R.layout.item_dropdown,
                    it
                )
                binding.projectDropdown.setAdapter(adapter)
                binding.projectDropdown.onItemClickListener =
                    AdapterView.OnItemClickListener { _, _, position, _ -> projectId = it[position]?.projectId ?: 0 }
            }
        )

        val adapter = TagAdapter(
            requireContext(),
            R.layout.item_tag_dropdown,
            enumValues()
        )
        binding.tagDropdown.setAdapter(adapter)
        binding.tagDropdown.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                tag = enumValues<Tag>()[position]
            }
    }

    private fun insertTask() {
        val name = binding.todoNameEditText.text.toString()
        val description = binding.todoDescriptionEditText.text.toString()
        if (projectId < 1 || binding.projectDropdown.text.isNullOrBlank()) {
            (activity as? MainActivity)?.showSnackbar("Error")
        } else {
            mainViewModel.insertTask(Task(name, description, projectId, tag, TaskState.DOING))
            activity?.hideSoftKeyboard()
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
