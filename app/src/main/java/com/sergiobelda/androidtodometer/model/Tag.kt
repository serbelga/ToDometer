package com.sergiobelda.androidtodometer.model

import androidx.annotation.ColorRes
import com.sergiobelda.androidtodometer.R

enum class Tag(
    val description: String,
    @ColorRes val resId: Int
) {
    DB("Persistence", R.color.pink),
    UI("UI/UX", R.color.red),
    AR_VR("AR/VR", R.color.indigo),
    ARCH("Architecture", R.color.blue),
    CAM("Camera", R.color.teal),
    MAPS("Maps", R.color.green),
    ML("Machine Learning & CV", R.color.lime),
    IOT("IoT", R.color.yellow),
    JETPACK("Jetpack Compose", R.color.amber),
    MEDIA("Media", R.color.orange),
    OTHER("Other", R.color.brown)
}