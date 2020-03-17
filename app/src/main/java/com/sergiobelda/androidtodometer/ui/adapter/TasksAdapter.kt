package com.sergiobelda.androidtodometer.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sergiobelda.androidtodometer.databaseview.ProjectTaskListing
import com.sergiobelda.androidtodometer.databinding.ItemTaskBinding
import com.sergiobelda.androidtodometer.model.Task

class TasksAdapter(private val items: List<ProjectTaskListing>) : RecyclerView.Adapter<TasksAdapter.ViewHolder>() {
    lateinit var taskClickListener: TaskClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(projectTaskListing: ProjectTaskListing) {
            binding.taskName.text = projectTaskListing.task.taskName
            binding.taskProjectId.text = projectTaskListing.projectName
            binding.deleteTaskButton.setOnClickListener {
                taskClickListener.deleteTaskClickListener(projectTaskListing.task)
            }
        }
    }

    interface TaskClickListener {
        fun deleteTaskClickListener(task: Task)
    }
}