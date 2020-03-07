package com.sergiobelda.androidtodometer.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.sergiobelda.androidtodometer.databinding.AddProjectFragmentBinding
import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.model.Task
import com.sergiobelda.androidtodometer.viewmodel.MainViewModel

/**
 *
 */
class AddProjectFragment : Fragment() {
    private var _binding: AddProjectFragmentBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddProjectFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addProjectButton.setOnClickListener {
            val name = binding.projectNameEditText.text.toString()
            mainViewModel.insertProject(Project(name, ""))
            //mainViewModel.insertTask(Task(name, "", 4))
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
