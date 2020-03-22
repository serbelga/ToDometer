package com.sergiobelda.androidtodometer.persistence

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.sergiobelda.androidtodometer.databaseview.ProjectTaskView
import com.sergiobelda.androidtodometer.databaseview.ProjectTaskListing

class ProjectTaskViewRepository(private val projectTaskViewDao: ProjectTaskViewDao) {
    val projectTaskView: LiveData<List<ProjectTaskView>> = projectTaskViewDao.getProjectTaskView()

    val projectTaskListingList: LiveData<PagedList<ProjectTaskListing>> = projectTaskViewDao.getProjectTaskListingList().toLiveData(pageSize = 10)

    fun getProjectTaskListing(id: Int): LiveData<ProjectTaskListing> {
        return projectTaskViewDao.getProjectTaskListing(id)
    }
}