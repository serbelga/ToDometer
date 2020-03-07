package com.sergiobelda.androidtodometer.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "task_table",
    foreignKeys = [ForeignKey(
        entity = Project::class,
        parentColumns = ["projectId"],
        childColumns = ["taskProjectId"],
        onDelete = CASCADE,
        onUpdate = CASCADE
    )]
)
data class Task(
    val name: String,
    val description: String?,
    val taskProjectId: Int?
) {
    @PrimaryKey(autoGenerate = true)
    var taskId: Int = 0
}