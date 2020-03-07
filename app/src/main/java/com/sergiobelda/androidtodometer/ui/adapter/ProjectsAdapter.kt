package com.sergiobelda.androidtodometer.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.model.Project

class ProjectsAdapter(var items: List<Project>) :
    RecyclerView.Adapter<ProjectsAdapter.ProjectViewHolder>() {

    lateinit var projectClickListener: ProjectClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        return ProjectViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_project, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: MaterialTextView = itemView.findViewById(R.id.project_name)
        private val deleteProjectButton: ImageButton = itemView.findViewById(R.id.delete_project_button)

        fun bind(project: Project) {
            name.text = project.name
            deleteProjectButton.setOnClickListener {
                projectClickListener.deleteProjectClickListener(project)
            }
        }
    }

    interface ProjectClickListener {
        fun deleteProjectClickListener(project: Project)
    }
}