package com.sergiobelda.androidtodometer.data.localdatasource

import com.sergiobelda.androidtodometer.data.database.dao.ProjectDao
import com.sergiobelda.androidtodometer.data.database.mapper.ProjectMapper.toDomain
import com.sergiobelda.androidtodometer.data.database.mapper.ProjectMapper.toEntity
import com.sergiobelda.androidtodometer.domain.model.Project
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProjectLocalDataSource(private val projectDao: ProjectDao) : IProjectLocalDataSource {

    override fun getProjects(): Flow<List<Project?>> = projectDao.getProjects().map { list ->
        list.map { it.toDomain() }
    }

    override fun getProject(id: Int): Flow<Project?> =
        projectDao.getProject(id).map { it.toDomain() }

    override suspend fun deleteProject(id: Int) = projectDao.deleteProject(id)

    override suspend fun insert(project: Project): Long? =
        project.toEntity()?.let { projectDao.insertProject(it) }

    override suspend fun updateProject(project: Project) {
        project.toEntity()?.let { projectDao.updateProject(it) }
    }
}
