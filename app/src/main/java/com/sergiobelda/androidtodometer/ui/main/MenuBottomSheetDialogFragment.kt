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
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.MenuBottomSheetDialogFragmentBinding
import com.sergiobelda.androidtodometer.domain.model.Project
import com.sergiobelda.androidtodometer.ui.adapter.ProjectsAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * [BottomSheetDialogFragment]
 */
@AndroidEntryPoint
class MenuBottomSheetDialogFragment : BottomSheetDialogFragment() {
    private var _binding: MenuBottomSheetDialogFragmentBinding? = null
    private val binding get() = _binding!!

    private val projectsAdapter = ProjectsAdapter()

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MenuBottomSheetDialogFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addProjectButton.setOnClickListener {
            dismiss()
            findNavController().navigate(R.id.addProjectFragment)
        }

        binding.projectsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.projectsRecyclerView.adapter = projectsAdapter
        mainViewModel.projects.observe(viewLifecycleOwner) {
            binding.noProjects.isVisible = it.isNullOrEmpty()
            projectsAdapter.submitList(it)
        }
        mainViewModel.projectSelectedId.observe(viewLifecycleOwner) {
            projectsAdapter.projectSelected = it
            projectsAdapter.notifyDataSetChanged()
        }
        projectsAdapter.projectClickListener = object : ProjectsAdapter.ProjectClickListener {
            override fun onProjectClick(project: Project) {
                mainViewModel.setProjectSelected(project.id)
                dismiss()
            }
        }
    }
}
