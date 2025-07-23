package com.example.vinote

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.vinote.health.HealthSettingsViewModel
import com.example.vinote.ui.dashboard.DashboardViewModel
import com.example.vinote.ui.editor.EditorViewModel
import com.example.vinote.ui.projects.ProjectsViewModel
import com.example.vinote.ui.settings.SettingsViewModel
import com.example.vinote.ui.settings.InterfaceSettingsViewModel
import com.example.vinote.ui.theme.ThemeViewModel
import com.example.vinote.ui.translate.TranslateViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ProjectsViewModel(vinoteApplication().container.projectsRepository)
        }
        initializer {
            EditorViewModel(vinoteApplication().container.chaptersRepository)
        }
        initializer {
            DashboardViewModel(vinoteApplication().container.chaptersRepository)
        }
        initializer {
            SettingsViewModel(vinoteApplication().container.cloudSyncServiceFactory)
        }
        initializer {
            ThemeViewModel()
        }
        initializer {
            HealthSettingsViewModel(vinoteApplication().applicationContext)
        }
        initializer {
            TranslateViewModel()
        }
        initializer {
            InterfaceSettingsViewModel()
        }
    }
}

fun CreationExtras.vinoteApplication(): VinoteApplication =
    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as VinoteApplication
