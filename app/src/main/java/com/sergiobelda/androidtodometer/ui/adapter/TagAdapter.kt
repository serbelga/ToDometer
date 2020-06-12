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

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.model.Tag

/**
 * [ArrayAdapter] to show all tags descriptions.
 */
class TagAdapter(context: Context, resource: Int, val items: Array<out Tag>) :
    ArrayAdapter<Tag>(context, resource, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tag_dropdown, parent, false)
        val imageView = view.findViewById<ImageView>(R.id.task_tag_color)
        imageView.setColorFilter(ContextCompat.getColor(context, items[position].resId))
        val textView = view.findViewById<TextView>(R.id.task_tag_description)
        textView.text = items[position].description
        return view
    }
}
