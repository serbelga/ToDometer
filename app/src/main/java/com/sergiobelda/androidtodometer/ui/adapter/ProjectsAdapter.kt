package com.sergiobelda.androidtodometer.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sergiobelda.androidtodometer.databinding.ItemProjectBinding
import com.sergiobelda.androidtodometer.model.Project

/**
 * [PagedListAdapter] to show a project list.
 */
class ProjectsAdapter : PagedListAdapter<Project, ProjectsAdapter.ProjectViewHolder>(DIFF_CALLBACK) {

    lateinit var projectClickListener: ProjectClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        return ProjectViewHolder(
            ItemProjectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false),
            parent.context
        )
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val project = getItem(position)
        project?.let { holder.bind(it) }
    }

    inner class ProjectViewHolder(private val binding: ItemProjectBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(project: Project) {
            binding.projectName.text = project.projectName
            binding.projectDescription.text = project.projectDescription
            binding.projectCard.transitionName = project.projectId.toString()
            binding.projectCard.setOnClickListener {
                projectClickListener.onProjectClick(project, it)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Project>() {
            override fun areItemsTheSame(oldProject: Project, newProject: Project): Boolean {
                return oldProject.projectId == newProject.projectId
            }

            override fun areContentsTheSame(oldProject: Project, newProject: Project): Boolean {
                return oldProject == newProject
            }
        }
    }

    interface ProjectClickListener {
        fun onProjectClick(project: Project, view: View)
    }
}