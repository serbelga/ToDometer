package com.sergiobelda.androidtodometer.ui

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
import androidx.fragment.app.activityViewModels
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.transition.MaterialContainerTransform
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.TaskFragmentBinding
import com.sergiobelda.androidtodometer.model.TaskState
import com.sergiobelda.androidtodometer.viewmodel.MainViewModel

/**
 * [Fragment] showing a task information.
 */
class TaskFragment : Fragment() {

    private lateinit var binding: TaskFragmentBinding

    private val args: TaskFragmentArgs by navArgs()

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.task_fragment, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = buildContainerTransform()
        sharedElementReturnTransition = buildContainerTransform()
    }

    private fun buildContainerTransform(): MaterialContainerTransform? {
        return MaterialContainerTransform(requireContext()).apply {
            drawingViewId = R.id.nav_host_fragment
            interpolator = FastOutSlowInInterpolator()
            fadeMode = MaterialContainerTransform.FADE_MODE_IN
            duration = resources.getInteger(R.integer.transition_duration).toLong()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.findViewById<FloatingActionButton>(R.id.create_button)?.setOnClickListener {
            val action = TaskFragmentDirections.navToEditTaskFragment(args.taskId)
            findNavController().navigate(action)
        }
        binding.taskCard.transitionName = args.taskId.toString()
        binding.taskDescription.movementMethod = ScrollingMovementMethod()
        mainViewModel.getProjectTaskListing(args.taskId).observe(viewLifecycleOwner, Observer {
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
        })
    }
}
