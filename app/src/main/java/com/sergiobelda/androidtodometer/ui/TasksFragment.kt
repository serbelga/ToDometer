package com.sergiobelda.androidtodometer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.sergiobelda.androidtodometer.databaseview.ProjectTaskListing
import com.sergiobelda.androidtodometer.databinding.TasksFragmentBinding
import com.sergiobelda.androidtodometer.model.Task
import com.sergiobelda.androidtodometer.ui.adapter.TasksAdapter
import com.sergiobelda.androidtodometer.ui.swipe.SwipeController
import com.sergiobelda.androidtodometer.viewmodel.MainViewModel

/**
 * TasksFragment
 */
class TasksFragment : Fragment() {
    private var _binding: TasksFragmentBinding? = null
    private val binding get() = _binding!!

    private val projectTaskList = arrayListOf<ProjectTaskListing>()
    private val tasksAdapter: TasksAdapter = TasksAdapter(projectTaskList)

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
        mainViewModel.projectTaskListingList.observe(viewLifecycleOwner, Observer {
            projectTaskList.clear()
            projectTaskList.addAll(it)
            if (projectTaskList.isNullOrEmpty()) {
                binding.emptyListImage.visibility = View.VISIBLE
                binding.emptyListMessage.visibility = View.VISIBLE
            } else {
                binding.emptyListImage.visibility = View.GONE
                binding.emptyListMessage.visibility = View.GONE
            }
            tasksAdapter.notifyDataSetChanged()
        })
        tasksAdapter.taskClickListener = object : TasksAdapter.TaskClickListener {
            override fun onTaskClick(task: Task, view: View) {
                val extras = FragmentNavigatorExtras(
                    view to task.taskName
                )
                val action = TasksFragmentDirections.navToTask(taskId = task.taskId, taskName = task.taskName)
                findNavController().navigate(action, extras)
            }

            override fun onDeleteTaskClick(task: Task) {}

            override fun onTaskDoneClick(task: Task) {
                mainViewModel.setTaskDone(task.taskId)
            }

            override fun onTaskDoingClick(task: Task) {
                mainViewModel.setTaskDoing(task.taskId)
            }
        }

        setSwipeActions()
    }

    private fun setSwipeActions() {
        val itemTouchHelper = ItemTouchHelper(SwipeController())
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
