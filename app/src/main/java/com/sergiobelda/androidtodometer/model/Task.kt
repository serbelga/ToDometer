package com.sergiobelda.androidtodometer.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(
    val name: String,
    val description: String?,
    val taskProjectId: Int?
) {
    @PrimaryKey(autoGenerate = true)
    var taskId: Int = 0
}