/*
 * Copyright 2021 Sergio Belda Galbis
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

package com.sergiobelda.androidtodometer.domain.model

import androidx.annotation.ColorRes
import com.sergiobelda.androidtodometer.R

enum class Tag(
    @ColorRes val resId: Int
) {
    GRAY(R.color.gray),

    PINK(R.color.pink),

    RED(R.color.red),

    INDIGO(R.color.indigo),

    BLUE(R.color.blue),

    TEAL(R.color.teal),

    GREEN(R.color.green),

    LIME(R.color.lime),

    YELLOW(R.color.yellow),

    AMBER(R.color.amber),

    ORANGE(R.color.orange),

    BROWN(R.color.brown)
}
