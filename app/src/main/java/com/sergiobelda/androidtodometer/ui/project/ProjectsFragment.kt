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
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.ProjectsFragmentBinding
import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.ui.adapter.ProjectsAdapter
import com.sergiobelda.androidtodometer.ui.swipe.SwipeController
import com.sergiobelda.androidtodometer.util.MaterialDialog
import com.sergiobelda.androidtodometer.util.MaterialDialog.Companion.icon
import com.sergiobelda.androidtodometer.util.MaterialDialog.Companion.message
import com.sergiobelda.androidtodometer.util.MaterialDialog.Companion.negativeButton
import com.sergiobelda.androidtodometer.util.MaterialDialog.Companion.positiveButton
import com.sergiobelda.androidtodometer.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * [Fragment] showing projects list.
 */
@AndroidEntryPoint
class ProjectsFragment : Fragment() {
    private var _binding: ProjectsFragmentBinding? = null
    private val binding get() = _binding!!

    private val projectsAdapter = ProjectsAdapter()

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProjectsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.projectsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.projectsRecyclerView.adapter = projectsAdapter
        mainViewModel.projects.observe(
            viewLifecycleOwner,
            Observer {
                if (it.isNullOrEmpty()) {
                    binding.emptyList.visibility = View.VISIBLE
                    removeToolbarScrollFlags()
                } else {
                    binding.emptyList.visibility = View.GONE
                    setToolbarScrollFlags()
                }
                projectsAdapter.submitList(it)
            }
        )
        projectsAdapter.projectClickListener = object : ProjectsAdapter.ProjectClickListener {
            override fun onProjectClick(project: Project, view: View) {
                val extras = FragmentNavigatorExtras(
                    view to project.projectId.toString()
                )
                val action =
                    ProjectsFragmentDirections.navToProject(
                        project.projectId
                    )
                findNavController().navigate(action, extras)
            }
        }
        setSwipeActions()
    }

    private fun removeToolbarScrollFlags() {
        (binding.toolbar.layoutParams as AppBarLayout.LayoutParams).scrollFlags = 0
    }

    private fun setToolbarScrollFlags() {
        (binding.toolbar.layoutParams as AppBarLayout.LayoutParams).scrollFlags =
            AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
    }

    private fun setSwipeActions() {
        val swipeController = SwipeController(
            requireContext(),
            object :
                SwipeController.SwipeControllerActions {
                override fun onDelete(position: Int) {
                    MaterialDialog.createDialog(requireContext()) {
                        icon(R.drawable.ic_warning_24dp)
                        message(R.string.delete_project_dialog)
                        positiveButton(getString(R.string.ok)) {
                            projectsAdapter.currentList?.get(position)?.projectId?.let { projectId ->
                                mainViewModel.deleteProject(
                                    projectId
                                )
                            }
                        }
                        negativeButton(getString(R.string.cancel))
                    }.show()
                }
            }
        )
        val itemTouchHelper = ItemTouchHelper(swipeController)
        itemTouchHelper.attachToRecyclerView(binding.projectsRecyclerView)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
