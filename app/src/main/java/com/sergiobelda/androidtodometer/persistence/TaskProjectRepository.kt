package com.sergiobelda.androidtodometer.persistence

import androidx.lifecycle.LiveData
import com.sergiobelda.androidtodometer.model.TaskProject

class TaskProjectRepository(private val taskProjectDao: TaskProjectDao) {
    val taskProjects: LiveData<List<TaskProject>> = taskProjectDao.getTaskProjects()
}