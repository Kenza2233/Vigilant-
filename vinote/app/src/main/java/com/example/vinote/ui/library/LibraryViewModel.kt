package com.example.vinote.ui.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinote.data.Project
import com.example.vinote.data.ProjectsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class LibraryViewModel(projectsRepository: ProjectsRepository) : ViewModel() {
    val libraryUiState: StateFlow<LibraryUiState> =
        projectsRepository.getAllProjectsStream().map { LibraryUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = LibraryUiState()
            )
}

data class LibraryUiState(val projectList: List<Project> = listOf())
