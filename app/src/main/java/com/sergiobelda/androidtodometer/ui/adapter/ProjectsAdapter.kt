package com.sergiobelda.androidtodometer.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sergiobelda.androidtodometer.databinding.ItemProjectBinding
import com.sergiobelda.androidtodometer.model.Project

class ProjectsAdapter(var items: List<Project>) :
    RecyclerView.Adapter<ProjectsAdapter.ProjectViewHolder>() {

    lateinit var projectClickListener: ProjectClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        return ProjectViewHolder(
            ItemProjectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ProjectViewHolder(private val binding: ItemProjectBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(project: Project) {
            binding.projectName.text = project.projectName
            binding.projectDescription.text = project.projectDescription
            binding.deleteProjectButton.setOnClickListener {
                projectClickListener.deleteProjectClickListener(project)
            }
        }
    }

    interface ProjectClickListener {
        fun deleteProjectClickListener(project: Project)
    }
}