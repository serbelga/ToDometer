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

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sergiobelda.androidtodometer.databinding.ItemProjectBinding
import com.sergiobelda.androidtodometer.domain.model.Project

/**
 * [ListAdapter] to show a project list.
 */
class ProjectsAdapter : ListAdapter<Project, ProjectsAdapter.ViewHolder>(DIFF_CALLBACK) {

    var projectClickListener: ProjectClickListener? = null

    var projectSelected: Int? = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemProjectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(private val binding: ItemProjectBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(project: Project) {
            binding.project = project
            binding.projectItem.isSelected = projectSelected == project.id
            binding.projectName.isSelected = projectSelected == project.id
            binding.projectItem.setOnClickListener {
                projectClickListener?.onProjectClick(project)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Project>() {
            override fun areItemsTheSame(oldProject: Project, newProject: Project): Boolean {
                return oldProject.id == newProject.id
            }

            override fun areContentsTheSame(oldProject: Project, newProject: Project): Boolean {
                return oldProject == newProject
            }
        }
    }

    interface ProjectClickListener {
        fun onProjectClick(project: Project)
    }
}
