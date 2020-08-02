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
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.EditTaskFragmentBinding
import com.sergiobelda.androidtodometer.model.Tag
import com.sergiobelda.androidtodometer.model.Task
import com.sergiobelda.androidtodometer.ui.adapter.TagAdapter
import com.sergiobelda.androidtodometer.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A [Fragment] to edit a Task.
 */
@AndroidEntryPoint
class EditTaskFragment : Fragment() {
    private lateinit var binding: EditTaskFragmentBinding

    private val mainViewModel by viewModels<MainViewModel>()

    private val args: EditTaskFragmentArgs by navArgs()

    private var mTask: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.edit_task_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editTaskButton.setOnClickListener {
            editTask()
        }
        val adapter = TagAdapter(
            requireContext(),
            R.layout.item_tag_dropdown,
            enumValues()
        )
        binding.tagDropdown.setAdapter(adapter)
        binding.tagDropdown.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                mTask?.tag = enumValues<Tag>()[position]
            }
        mainViewModel.getProjectTaskListing(args.taskId).observe(
            viewLifecycleOwner,
            Observer { projectTaskListing ->
                mTask = projectTaskListing.task
                binding.task = mTask
                binding.taskProjectEditText.setText(projectTaskListing.projectName)
                mTask?.tag?.let {
                    binding.tagDropdown.setText(it.description, false)
                }
            }
        )
    }

    private fun editTask() {
        mTask?.let {
            it.taskName = binding.taskNameEditText.text.toString()
            it.taskDescription = binding.taskDescriptionEditText.text.toString()
            mainViewModel.updateTask(it)
            findNavController().navigateUp()
        }
    }
}
