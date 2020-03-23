package com.sergiobelda.androidtodometer.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton

import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.EditTaskFragmentBinding
import com.sergiobelda.androidtodometer.model.Task
import com.sergiobelda.androidtodometer.viewmodel.MainViewModel

class EditTaskFragment : Fragment() {
    private var _binding: EditTaskFragmentBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModels()

    private val args: EditTaskFragmentArgs by navArgs()

    private var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = EditTaskFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.findViewById<FloatingActionButton>(R.id.create_button)?.setOnClickListener {
            editTask()
        }
        mainViewModel.getProjectTaskListing(args.taskId).observe(viewLifecycleOwner, Observer {
            task = it.task
            binding.task = task
        })
    }

    private fun editTask() {
        task?.let {
            it.taskName = binding.taskNameEditText.text.toString()
            it.taskDescription = binding.taskDescriptionEditText.text.toString()
            mainViewModel.updateTask(it)
            val action = EditTaskFragmentDirections.navToTasksFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
