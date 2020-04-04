package com.sergiobelda.androidtodometer.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton

import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.EditProjectFragmentBinding
import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.viewmodel.MainViewModel

/**
 * A [Fragment] to edit a project.
 */
class EditProjectFragment : Fragment() {
    private lateinit var binding: EditProjectFragmentBinding

    private val mainViewModel: MainViewModel by activityViewModels()

    private val args: EditProjectFragmentArgs by navArgs()

    private var mProject: Project? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.edit_project_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.findViewById<FloatingActionButton>(R.id.create_button)?.setOnClickListener {
            editProject()
        }
        mainViewModel.getProject(args.projectId).observe(viewLifecycleOwner, Observer {
            mProject = it
            binding.project = mProject
        })
    }

    private fun editProject() {
        mProject?.let {
            it.projectName = binding.projectNameEditText.text.toString()
            it.projectDescription = binding.projectDescriptionEditText.text.toString()
            mainViewModel.updateProject(it)
            val action = EditProjectFragmentDirections.navToProjectsFragment()
            findNavController().navigate(action)
        }
    }
}
