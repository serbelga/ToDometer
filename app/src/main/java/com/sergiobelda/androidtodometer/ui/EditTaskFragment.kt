package com.sergiobelda.androidtodometer.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton

import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.EditTaskFragmentBinding
import com.sergiobelda.androidtodometer.model.Tag
import com.sergiobelda.androidtodometer.model.Task
import com.sergiobelda.androidtodometer.ui.adapter.TagAdapter
import com.sergiobelda.androidtodometer.viewmodel.MainViewModel

class EditTaskFragment : Fragment() {
    private var _binding: EditTaskFragmentBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModels()

    private val args: EditTaskFragmentArgs by navArgs()

    private var mTask: Task? = null

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
        mainViewModel.getProjectTaskListing(args.taskId).observe(viewLifecycleOwner, Observer { projectTaskListing ->
            mTask = projectTaskListing.task
            binding.task = mTask
            binding.taskProjectEditText.setText(projectTaskListing.projectName)
            mTask?.tag?.let {
                binding.tagDropdown.setText(it.description, false)
            }
        })
    }

    private fun editTask() {
        mTask?.let {
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
