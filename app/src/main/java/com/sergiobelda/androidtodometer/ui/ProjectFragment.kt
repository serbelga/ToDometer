package com.sergiobelda.androidtodometer.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.transition.MaterialContainerTransform

import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.ProjectFragmentBinding
import com.sergiobelda.androidtodometer.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * [Fragment] showing project information and its related tasks
 */
class ProjectFragment : Fragment() {

    private lateinit var binding: ProjectFragmentBinding

    private val args: ProjectFragmentArgs by navArgs()

    private val mainViewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.project_fragment, container, false)
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
        activity?.findViewById<FloatingActionButton>(R.id.create_button)?.setOnClickListener {
            val action = ProjectFragmentDirections.navToEditProjectFragment(args.projectId)
            findNavController().navigate(action)
        }
        binding.projectCard.transitionName = args.projectId.toString()
        mainViewModel.getProject(args.projectId).observe(viewLifecycleOwner, Observer {
            binding.project = it
        })
    }

    private fun buildContainerTransform(): MaterialContainerTransform? {
        return MaterialContainerTransform(requireContext()).apply {
            drawingViewId = R.id.nav_host_fragment
            interpolator = FastOutSlowInInterpolator()
            fadeMode = MaterialContainerTransform.FADE_MODE_IN
            duration = resources.getInteger(R.integer.transition_duration).toLong()
        }
    }
}
