package com.example.vinote.ui.projects

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinote.data.Project
import com.example.vinote.data.ProjectsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProjectsViewModel(private val projectsRepository: ProjectsRepository) : ViewModel() {
    val projectsUiState: StateFlow<ProjectsUiState> =
        projectsRepository.getAllProjectsStream().map { ProjectsUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ProjectsUiState()
            )

    fun addProject(projectName: String, projectDescription: String) {
        viewModelScope.launch {
            projectsRepository.insertProject(Project(name = projectName, description = projectDescription))
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class ProjectsUiState(val projectList: List<Project> = listOf())
