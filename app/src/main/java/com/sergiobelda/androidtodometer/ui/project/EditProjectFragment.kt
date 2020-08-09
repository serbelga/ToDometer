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
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.EditProjectFragmentBinding
import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A [Fragment] to edit a Project.
 */
@AndroidEntryPoint
class EditProjectFragment : Fragment() {
    private lateinit var binding: EditProjectFragmentBinding

    private val mainViewModel by viewModels<MainViewModel>()

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
        binding.editProjectButton.setOnClickListener {
            editProject()
        }
        mainViewModel.getProject(args.projectId).observe(
            viewLifecycleOwner,
            Observer {
                mProject = it
                binding.project = mProject
            }
        )
    }

    private fun editProject() {
        mProject?.let {
            it.projectName = binding.projectNameEditText.text.toString()
            it.projectDescription = binding.projectDescriptionEditText.text.toString()
            mainViewModel.updateProject(it)
            findNavController().navigateUp()
        }
    }
}
