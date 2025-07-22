package com.example.vinote

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.vinote.ui.projects.ProjectsViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ProjectsViewModel(vinoteApplication().container.projectsRepository)
        }
    }
}

fun CreationExtras.vinoteApplication(): VinoteApplication =
    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as VinoteApplication
