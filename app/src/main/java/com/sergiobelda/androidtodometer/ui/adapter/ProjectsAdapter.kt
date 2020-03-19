package com.sergiobelda.androidtodometer.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.ItemProjectBinding
import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.util.MaterialDialog
import com.sergiobelda.androidtodometer.util.MaterialDialog.Companion.icon
import com.sergiobelda.androidtodometer.util.MaterialDialog.Companion.message
import com.sergiobelda.androidtodometer.util.MaterialDialog.Companion.negativeButton
import com.sergiobelda.androidtodometer.util.MaterialDialog.Companion.positiveButton

class ProjectsAdapter(private var items: List<Project>) :
    RecyclerView.Adapter<ProjectsAdapter.ProjectViewHolder>() {

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

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ProjectViewHolder(private val binding: ItemProjectBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(project: Project) {
            binding.projectName.text = project.projectName
            binding.projectDescription.text = project.projectDescription
            binding.deleteProjectButton.setOnClickListener {
                MaterialDialog.createDialog(context) {
                    icon(R.drawable.ic_warning_24dp)
                    message(context.getString(R.string.delete_project_dialog))
                    positiveButton(context.getString(R.string.yes)) {
                        projectClickListener.deleteProjectClickListener(project)
                    }
                    negativeButton(context.getString(R.string.cancel))
                }.show()
            }
        }
    }

    interface ProjectClickListener {
        fun deleteProjectClickListener(project: Project)
    }
}