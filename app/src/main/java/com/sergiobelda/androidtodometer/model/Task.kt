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
    var taskName: String,
    var taskDescription: String?,
    val taskProjectId: Int?,
    var tag: Tag?,
    val taskState: TaskState
) {
    @PrimaryKey(autoGenerate = true)
    var taskId: Int = 0
}