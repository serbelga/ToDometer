package com.sergiobelda.androidtodometer.persistence

import androidx.lifecycle.LiveData
import com.sergiobelda.androidtodometer.databaseview.ProjectTaskView
import com.sergiobelda.androidtodometer.databaseview.ProjectTaskListing

class ProjectTaskViewRepository(private val projectTaskViewDao: ProjectTaskViewDao) {
    val projectTaskView: LiveData<List<ProjectTaskView>> = projectTaskViewDao.getProjectTaskView()

    val projectTaskListingList: LiveData<List<ProjectTaskListing>> = projectTaskViewDao.getProjectTaskListingList()

    fun getProjectTaskListing(id: Int): LiveData<ProjectTaskListing> {
        return projectTaskViewDao.getProjectTaskListing(id)
    }
}