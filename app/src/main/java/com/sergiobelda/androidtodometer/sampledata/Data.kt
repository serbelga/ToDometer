package com.sergiobelda.androidtodometer.sampledata

import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.model.Status
import com.sergiobelda.androidtodometer.model.Tag
import com.sergiobelda.androidtodometer.model.Task

val project1 = Project(
    name = "Gramophone",
    description = "Music Player"
)

val project2 = Project(
    name = "Android_Codelab",
    description = "Playground for Android"
)

val task1 = Task(
    id = 1,
    name = "Retrofit+Coroutines",
    description = "",
    projectId = 2,
    tags = arrayListOf(Tag.Architecture),
    status = Status.Doing
)