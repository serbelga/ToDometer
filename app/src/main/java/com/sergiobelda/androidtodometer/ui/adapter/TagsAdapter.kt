/*
 * Copyright 2021 Sergio Belda
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
import androidx.recyclerview.widget.RecyclerView
import com.sergiobelda.androidtodometer.databinding.ItemTagBinding
import com.sergiobelda.androidtodometer.domain.model.Tag

class TagsAdapter(private val items: List<Tag>) :
    RecyclerView.Adapter<TagsAdapter.ViewHolder>() {

    var tagSelectedPosition: Int = 0

    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemTagBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items.getOrNull(position)?.let { holder.bind(it, position) }
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemTagBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tag: Tag, position: Int) {
            binding.tag = tag
            binding.isSelected = tagSelectedPosition == position
            binding.tagColor.setOnClickListener {
                val previousPosition = tagSelectedPosition
                tagSelectedPosition = position

                notifyItemChanged(previousPosition)
                notifyItemChanged(tagSelectedPosition)

                listener?.onTagClick(tag)
            }
        }
    }

    fun interface Listener {
        fun onTagClick(tag: Tag)
    }
}
