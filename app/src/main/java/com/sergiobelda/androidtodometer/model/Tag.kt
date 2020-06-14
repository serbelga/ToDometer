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
    WEB("Web", R.color.blue500),
    CAM("Camera", R.color.teal),
    MAPS("Maps", R.color.green),
    ML("Machine Learning & CV", R.color.lime),
    IOT("IoT", R.color.yellow),
    JETPACK("Jetpack Compose", R.color.amber),
    MEDIA("Media", R.color.orange),
    KOTLIN_NATIVE("Kotlin/Native", R.color.deep_orange400),
    KOTLIN_JS("Kotlin/JS", R.color.deep_orange400),
    OTHER("Other", R.color.brown);

    override fun toString(): String {
        return description
    }
}
