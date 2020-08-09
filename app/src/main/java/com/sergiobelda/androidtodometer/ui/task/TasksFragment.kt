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
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databaseview.ProjectTaskListing
import com.sergiobelda.androidtodometer.databinding.TasksFragmentBinding
import com.sergiobelda.androidtodometer.model.Task
import com.sergiobelda.androidtodometer.model.TaskState
import com.sergiobelda.androidtodometer.ui.adapter.TasksAdapter
import com.sergiobelda.androidtodometer.ui.swipe.SwipeController
import com.sergiobelda.androidtodometer.util.MaterialDialog
import com.sergiobelda.androidtodometer.util.MaterialDialog.Companion.icon
import com.sergiobelda.androidtodometer.util.MaterialDialog.Companion.message
import com.sergiobelda.androidtodometer.util.MaterialDialog.Companion.negativeButton
import com.sergiobelda.androidtodometer.util.MaterialDialog.Companion.positiveButton
import com.sergiobelda.androidtodometer.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * [Fragment] showing the list of tasks.
 */
@AndroidEntryPoint
class TasksFragment : Fragment() {
    private var _binding: TasksFragmentBinding? = null
    private val binding get() = _binding!!

    private val tasksAdapter: TasksAdapter = TasksAdapter()

    private val mainViewModel by viewModels<MainViewModel>()
    // NOTE: using Koin we can write also:
    // private val mainViewModel by lazy { getViewModel<MainViewModel>() }
    // Using fragment-ktx extension:
    // private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TasksFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tasksRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.tasksRecyclerView.adapter = tasksAdapter
        mainViewModel.projectTaskListingList.observe(
            viewLifecycleOwner,
            Observer { list ->
                if (list.isNullOrEmpty()) {
                    binding.emptyList.visibility = View.VISIBLE
                    removeToolbarScrollFlags()
                } else {
                    binding.emptyList.visibility = View.GONE
                    setToolbarScrollFlags()
                }
                tasksAdapter.submitList(list)
                val progress = getTasksDoneProgress(list)
                binding.progressBar.progress = progress
                binding.progressTextView.text = "$progress%"
            }
        )
        tasksAdapter.taskClickListener = object : TasksAdapter.TaskClickListener {
            override fun onTaskClick(task: Task, view: View) {
                val extras = FragmentNavigatorExtras(
                    view to task.taskId.toString()
                )
                val action =
                    TasksFragmentDirections.navToTask(
                        taskId = task.taskId
                    )
                findNavController().navigate(action, extras)
            }

            override fun onTaskDoneClick(task: Task) {
                mainViewModel.setTaskDone(task.taskId)
            }

            override fun onTaskDoingClick(task: Task) {
                mainViewModel.setTaskDoing(task.taskId)
            }
        }

        setSwipeActions()
    }

    private fun getTasksDoneProgress(list: PagedList<ProjectTaskListing>): Int {
        val doneCount = list.filter { it.task.taskState == TaskState.DONE }.size
        return ((doneCount.toDouble() / list.size.toDouble()) * 100).toInt()
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
                        message(R.string.delete_task_dialog)
                        positiveButton(getString(R.string.ok)) {
                            tasksAdapter.currentList?.get(position)?.task?.taskId?.let { taskId ->
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

    companion object {
        private const val TAG = "TasksFragment"
    }
}
