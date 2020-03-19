package com.sergiobelda.androidtodometer.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

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
    val taskName: String,
    val taskDescription: String?,
    val taskProjectId: Int?,
    val tag: Tag?
) {
    @PrimaryKey(autoGenerate = true)
    var taskId: Int = 0
}