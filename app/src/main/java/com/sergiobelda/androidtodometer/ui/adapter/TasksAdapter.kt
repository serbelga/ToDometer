package com.sergiobelda.androidtodometer.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.model.Task

class TasksAdapter(val items: List<Task>) : RecyclerView.Adapter<TasksAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: MaterialTextView = itemView.findViewById(R.id.task_name)
        private val taskProjectId: MaterialTextView = itemView.findViewById(R.id.task_project_id)
        fun bind(task: Task) {
            name.text = task.name
            taskProjectId.text = task.taskProjectId.toString()
        }
    }
}