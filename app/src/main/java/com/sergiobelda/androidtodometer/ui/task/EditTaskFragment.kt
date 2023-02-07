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
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.EditTaskFragmentBinding
import com.sergiobelda.androidtodometer.domain.model.Tag
import com.sergiobelda.androidtodometer.domain.model.Task
import com.sergiobelda.androidtodometer.extensions.hideSoftKeyboard
import com.sergiobelda.androidtodometer.ui.adapter.TagsAdapter
import com.sergiobelda.androidtodometer.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import dev.sergiobelda.android.companion.material.clearError

/**
 * A [Fragment] to edit a Task.
 */
@AndroidEntryPoint
class EditTaskFragment : Fragment() {
    private lateinit var binding: EditTaskFragmentBinding

    private val mainViewModel by viewModels<MainViewModel>()

    private val args: EditTaskFragmentArgs by navArgs()

    private var task: Task? = null

    private var selectedTag: Tag? = null

    private val tags = enumValues<Tag>().toList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.edit_task_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
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
                        if (validateTaskName()) {
                            editTask()
                        }
                        true
                    }
                    else -> false
                }
            }
        }
        binding.tagList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        mainViewModel.getTask(args.taskId).observe(viewLifecycleOwner) {
            task = it
            binding.task = task
            task?.tag?.let { tag ->
                val selected = tags.indexOf(tag)
                selectedTag = tag
                binding.tagList.adapter = TagsAdapter(tags).apply {
                    tagSelectedPosition = selected
                    listener = TagsAdapter.Listener {
                        selectedTag = it
                    }
                }
                binding.tagList.scrollToPosition(selected)
            }
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

    private fun editTask() {
        task?.let {
            mainViewModel.updateTask(
                Task(
                    it.id,
                    binding.taskNameEditText.text.toString(),
                    binding.taskDescriptionEditText.text.toString(),
                    it.state,
                    it.projectId,
                    selectedTag
                )
            )
            activity?.hideSoftKeyboard()
            findNavController().navigateUp()
        }
    }
}
