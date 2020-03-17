package com.sergiobelda.androidtodometer.model

import androidx.room.Embedded
import androidx.room.Relation

data class ProjectTask(
    @Embedded val project: Project,
    @Relation(
        parentColumn = "projectId",
        entityColumn = "taskProjectId"
    )
    val tasks: List<Task>
)