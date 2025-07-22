package com.example.vinote.data

import kotlinx.coroutines.flow.Flow

class ProjectsRepository(private val projectDao: ProjectDao) {
    fun getAllProjectsStream(): Flow<List<Project>> = projectDao.getAllProjects()

    suspend fun insertProject(project: Project) = projectDao.insert(project)

    suspend fun deleteProject(project: Project) = projectDao.delete(project)

    suspend fun updateProject(project: Project) = projectDao.update(project)
}
