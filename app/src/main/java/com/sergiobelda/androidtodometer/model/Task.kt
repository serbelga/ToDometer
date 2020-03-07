package com.sergiobelda.androidtodometer.model

data class Task(
    val id: Int,
    val name: String,
    val description: String?,
    val projectId: Int?,
    val tags: ArrayList<Tag>?,
    val status: Status?
)