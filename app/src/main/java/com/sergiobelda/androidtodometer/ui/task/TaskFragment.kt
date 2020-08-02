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

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.ScrollingMovementMethod
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionManager
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialFade
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.TaskFragmentBinding
import com.sergiobelda.androidtodometer.model.TaskState
import com.sergiobelda.androidtodometer.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * [Fragment] showing the info for some task.
 */
@AndroidEntryPoint
class TaskFragment : Fragment() {

    private lateinit var binding: TaskFragmentBinding

    private val args: TaskFragmentArgs by navArgs()

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.task_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = buildContainerTransform()
        sharedElementReturnTransition = buildContainerTransform()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editButton.apply {
            postDelayed(
                {
                    val transition = MaterialFade().apply {
                        duration = resources.getInteger(R.integer.fade_transition_duration).toLong()
                    }
                    TransitionManager.beginDelayedTransition(requireActivity().findViewById(android.R.id.content), transition)
                    visibility = View.VISIBLE
                },
                200
            )
            setOnClickListener {
                val action =
                    TaskFragmentDirections.navToEditTaskFragment(
                        args.taskId
                    )
                findNavController().navigate(action)
            }
        }
        binding.taskCard.transitionName = args.taskId.toString()
        binding.taskDescription.movementMethod = ScrollingMovementMethod()
        mainViewModel.getProjectTaskListing(args.taskId).observe(
            viewLifecycleOwner,
            Observer {
                binding.task = it.task
                binding.taskProjectName.text = it.projectName
                it.task.tag?.resId?.let { resId ->
                    binding.taskTagColor.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            resId
                        )
                    )
                }
                if (it.task.taskState == TaskState.DONE) {
                    val spannableString = SpannableString(it.task.taskName)
                    spannableString.setSpan(
                        StrikethroughSpan(),
                        0,
                        spannableString.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    binding.taskNameTextView.text = spannableString
                }
            }
        )
    }

    private fun buildContainerTransform(): MaterialContainerTransform? {
        return MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment
            interpolator = FastOutSlowInInterpolator()
            fadeMode = MaterialContainerTransform.FADE_MODE_IN
            duration = resources.getInteger(R.integer.container_transform_duration).toLong()
            scrimColor = Color.TRANSPARENT
        }
    }
}
