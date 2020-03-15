package com.sergiobelda.androidtodometer.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.sergiobelda.androidtodometer.databinding.ProjectsFragmentBinding
import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.ui.adapter.ProjectsAdapter
import com.sergiobelda.androidtodometer.viewmodel.MainViewModel

/**
 * ProjectsFragment
 */
class ProjectsFragment : Fragment() {
    private var _binding: ProjectsFragmentBinding? = null
    private val binding get() = _binding!!

    private val projects = arrayListOf<Project>()
    private val projectsAdapter = ProjectsAdapter(projects)

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProjectsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.projectsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.projectsRecyclerView.adapter = projectsAdapter
        mainViewModel.projects.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                binding.emptyListImage.visibility = View.VISIBLE
                binding.emptyListMessage.visibility = View.VISIBLE
                projects.clear()
            } else {
                binding.emptyListImage.visibility = View.GONE
                binding.emptyListMessage.visibility = View.GONE
                projects.clear()
                projects.addAll(it)
            }
            projectsAdapter.notifyDataSetChanged()
        })
        projectsAdapter.projectClickListener = object : ProjectsAdapter.ProjectClickListener {
            override fun deleteProjectClickListener(project: Project) {
                mainViewModel.deleteProject(project.projectId)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
