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

package com.sergiobelda.androidtodometer.ui.adapter

import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.sergiobelda.androidtodometer.databinding.ItemTaskBinding
import com.sergiobelda.androidtodometer.domain.model.Task
import com.sergiobelda.androidtodometer.domain.model.TaskState

/**
 * [ListAdapter] to show a list of tasks.
 */
class TasksAdapter : ListAdapter<Task, TasksAdapter.ViewHolder>(DIFF_CALLBACK) {
    lateinit var taskClickListener: TaskClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.task = task
            binding.taskCard.transitionName = task.id.toString()
            if (task.state == TaskState.DOING) {
                binding.checkTaskButton.setOnClickListener {
                    taskClickListener.onTaskDoneClick(task)
                }
                binding.taskNameTextView.text = task.name
            } else {
                binding.checkTaskButton.setOnClickListener {
                    taskClickListener.onTaskDoingClick(task)
                }
                val spannableString = SpannableString(task.name)
                spannableString.setSpan(
                    StrikethroughSpan(),
                    0,
                    spannableString.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                binding.taskNameTextView.text = spannableString
            }
            binding.taskCard.setOnClickListener {
                taskClickListener.onTaskClick(task.id, binding.taskCard)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Task>() {

            override fun areItemsTheSame(oldTask: Task, newTask: Task) =
                oldTask.id == newTask.id

            override fun areContentsTheSame(oldTask: Task, newTask: Task) =
                oldTask == newTask
        }
    }

    interface TaskClickListener {
        fun onTaskClick(taskId: Int, card: MaterialCardView)
        fun onTaskDoneClick(task: Task)
        fun onTaskDoingClick(task: Task)
    }
}
