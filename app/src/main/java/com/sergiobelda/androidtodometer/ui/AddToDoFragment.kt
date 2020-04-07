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

package com.sergiobelda.androidtodometer.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.AddToDoFragmentBinding
import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.model.Tag
import com.sergiobelda.androidtodometer.model.Task
import com.sergiobelda.androidtodometer.model.TaskState
import com.sergiobelda.androidtodometer.ui.adapter.TagAdapter
import com.sergiobelda.androidtodometer.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * [Fragment] to add a to do
 */
class AddToDoFragment : Fragment() {
    private var _binding: AddToDoFragmentBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel by sharedViewModel<MainViewModel>()

    private var projectId = 0
    private var tag = Tag.OTHER

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddToDoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    TASK -> {
                        binding.tagDropdownInput.visibility = View.VISIBLE
                        binding.projectDropdownInput.visibility = View.VISIBLE
                        activity?.findViewById<FloatingActionButton>(R.id.create_button)?.setOnClickListener {
                            insertTask()
                        }
                    }
                    PROJECT -> {
                        binding.tagDropdownInput.visibility = View.GONE
                        binding.projectDropdownInput.visibility = View.GONE
                        activity?.findViewById<FloatingActionButton>(R.id.create_button)?.setOnClickListener {
                            insertProject()
                        }
                    }
                }
            }
        })
        activity?.findViewById<FloatingActionButton>(R.id.create_button)?.setOnClickListener {
            insertTask()
        }
        mainViewModel.projects.observe(viewLifecycleOwner, Observer {
            val adapter = ArrayAdapter(
                requireContext(),
                R.layout.item_dropdown,
                it
            )
            binding.projectDropdown.setAdapter(adapter)
            binding.projectDropdown.onItemClickListener =
                AdapterView.OnItemClickListener { _, _, position, _ -> projectId = it[position]?.projectId ?: 0 }
        })

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

    private fun insertProject() {
        val name = binding.todoNameEditText.text.toString()
        val description = binding.todoDescriptionEditText.text.toString()
        mainViewModel.insertProject(Project(name, description))
        val action = AddToDoFragmentDirections.returnToProjectsFragment()
        findNavController().navigate(action)
    }

    private fun insertTask() {
        val name = binding.todoNameEditText.text.toString()
        val description = binding.todoDescriptionEditText.text.toString()
        if (projectId < 1 || binding.projectDropdown.text.isNullOrBlank()) {
            (activity as? MainActivity)?.showSnackbar("Error")
        } else {
            mainViewModel.insertTask(Task(name, description, projectId, tag, TaskState.DOING))
            val action = AddToDoFragmentDirections.returnToTasksFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private const val TASK = 0
        private const val PROJECT = 1
        private const val TAG = "AddToDo"
    }
}
