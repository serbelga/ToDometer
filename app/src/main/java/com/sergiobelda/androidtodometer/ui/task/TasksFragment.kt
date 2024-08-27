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

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.card.MaterialCardView
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.TasksFragmentBinding
import com.sergiobelda.androidtodometer.domain.model.Task
import com.sergiobelda.androidtodometer.ui.adapter.TasksAdapter
import com.sergiobelda.androidtodometer.ui.main.MainViewModel
import com.sergiobelda.androidtodometer.ui.swipe.SwipeController
import com.sergiobelda.androidtodometer.ui.util.ProgressUtil.getPercentage
import com.sergiobelda.androidtodometer.ui.util.ProgressUtil.getTasksDoneProgress
import dagger.hilt.android.AndroidEntryPoint
import dev.sergiobelda.android.companion.material.createMaterialDialog
import dev.sergiobelda.android.companion.material.icon
import dev.sergiobelda.android.companion.material.message
import dev.sergiobelda.android.companion.material.negativeButton
import dev.sergiobelda.android.companion.material.positiveButton
import dev.sergiobelda.android.companion.material.title

/**
 * [Fragment] showing the list of tasks.
 */
@AndroidEntryPoint
class TasksFragment : Fragment() {
    private var _binding: TasksFragmentBinding? = null
    private val binding get() = _binding!!

    private val tasksAdapter = TasksAdapter()

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TasksFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        initTasksRecyclerView()
        initProjectSelectedObserver()
        setSwipeActions()
    }

    private fun initProjectSelectedObserver() {
        mainViewModel.projectSelected.observe(viewLifecycleOwner) { project ->
            binding.projectNameTextView.text = project?.name ?: "-"
            project?.tasks?.let {
                if (it.isEmpty()) {
                    showEmptyListIllustration()
                } else {
                    hideEmptyListIllustration()
                }
                tasksAdapter.submitList(it)
                setProgressValue(getTasksDoneProgress(it))
            }
        }
    }

    private fun setProgressValue(progress: Int) {
        ObjectAnimator.ofInt(
            binding.progressBar,
            "progress",
            binding.progressBar.progress,
            progress
        ).apply {
            duration = resources.getInteger(R.integer.progress_bar_animation).toLong()
            interpolator = AccelerateInterpolator()
        }.start()
        binding.progressTextView.text = getPercentage(progress)
    }

    private fun showEmptyListIllustration() {
        binding.emptyList.visibility = View.VISIBLE
        removeToolbarScrollFlags()
    }

    private fun hideEmptyListIllustration() {
        binding.emptyList.visibility = View.GONE
        setToolbarScrollFlags()
    }

    private fun initTasksRecyclerView() {
        binding.tasksRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = tasksAdapter
        }
        binding.tasksRecyclerView.doOnPreDraw {
            startPostponedEnterTransition()
        }
        tasksAdapter.taskClickListener = object : TasksAdapter.TaskClickListener {
            override fun onTaskClick(taskId: Int, card: MaterialCardView) {
                val extras = FragmentNavigatorExtras(
                    card to taskId.toString()
                )
                val action = TasksFragmentDirections.navToTask(
                    taskId = taskId
                )
                findNavController().navigate(action, extras)
            }

            override fun onTaskDoneClick(task: Task) {
                mainViewModel.setTaskDone(task.id)
            }

            override fun onTaskDoingClick(task: Task) {
                mainViewModel.setTaskDoing(task.id)
            }
        }
    }

    private fun removeToolbarScrollFlags() {
        (binding.toolbar.layoutParams as AppBarLayout.LayoutParams).scrollFlags = 0
    }

    private fun setToolbarScrollFlags() {
        (binding.toolbar.layoutParams as AppBarLayout.LayoutParams).scrollFlags =
            AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or
                    AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP or
                    AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
    }

    private fun setSwipeActions() {
        val swipeController = SwipeController(
            requireContext(),
            object :
                SwipeController.SwipeControllerActions {
                override fun onDelete(position: Int) {
                    createMaterialDialog(
                        requireContext(),
                        style = R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_Centered
                    ) {
                        icon(R.drawable.ic_warning_24dp)
                        title(" ")
                        message(R.string.delete_task_dialog)
                        positiveButton(getString(R.string.accept)) {
                            tasksAdapter.currentList[position]?.id?.let { taskId ->
                                mainViewModel.deleteTask(
                                    taskId
                                )
                            }
                        }
                        negativeButton(getString(R.string.cancel))
                    }.show()
                }
            }
        )
        val itemTouchHelper = ItemTouchHelper(swipeController)
        itemTouchHelper.attachToRecyclerView(binding.tasksRecyclerView)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
