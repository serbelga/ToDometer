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