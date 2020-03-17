package com.sergiobelda.androidtodometer.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sergiobelda.androidtodometer.databaseview.ProjectTaskListing

import com.sergiobelda.androidtodometer.databinding.TasksFragmentBinding
import com.sergiobelda.androidtodometer.model.Task
import com.sergiobelda.androidtodometer.ui.adapter.TasksAdapter
import com.sergiobelda.androidtodometer.viewmodel.MainViewModel

/**
 * TasksFragment
 */
class TasksFragment : Fragment() {
    private var _binding: TasksFragmentBinding? = null
    private val binding get() = _binding!!

    private val projectTaskList = arrayListOf<ProjectTaskListing>()
    private val tasksAdapter = TasksAdapter(projectTaskList)

    private val mainViewModel: MainViewModel by activityViewModels()

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
        mainViewModel.projectTaskListing.observe(viewLifecycleOwner, Observer {
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
            override fun deleteTaskClickListener(task: Task) {
                mainViewModel.deleteTask(task.taskId)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private const val TAG = "TasksFragment"
    }
}
