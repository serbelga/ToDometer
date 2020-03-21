package com.sergiobelda.androidtodometer.databinding

import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("tagColor")
fun bindTagColor(imageView: ImageView, @ColorRes resId: Int) {
    imageView.setColorFilter(ContextCompat.getColor(imageView.context, resId))
}
