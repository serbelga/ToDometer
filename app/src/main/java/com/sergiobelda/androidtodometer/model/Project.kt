package com.sergiobelda.androidtodometer.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "project_table")
data class Project(
    val projectName: String,
    val projectDescription: String
) {
    @PrimaryKey(autoGenerate = true)
    var projectId: Int = 0

    override fun toString(): String {
        return projectName
    }
}