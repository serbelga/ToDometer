package com.sergiobelda.androidtodometer.databaseview

import androidx.room.DatabaseView
import androidx.room.Embedded
import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.model.Task

@DatabaseView(
    "SELECT project_table.*, task_table.* FROM project_table INNER JOIN task_table ON project_table.projectId = task_table.taskProjectId"
)
data class ProjectTaskView(
    @Embedded
    val project: Project,
    @Embedded
    val task: Task
)

data class ProjectTaskListing(
    val projectId: Int,
    val projectName: String,
    @Embedded
    val task: Task
)