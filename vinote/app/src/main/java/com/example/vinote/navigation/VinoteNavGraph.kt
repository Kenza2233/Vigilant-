package com.example.vinote.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.composable
import com.example.vinote.AppViewModelProvider
import com.example.vinote.ui.dashboard.DashboardScreen
import com.example.vinote.ui.editor.EditorScreen
import com.example.vinote.ui.projects.AddProjectScreen
import com.example.vinote.ui.projects.ProjectsScreen
import com.example.vinote.ui.projects.ProjectsViewModel

enum class VinoteScreen {
    Projects,
    AddProject,
    Editor,
    Dashboard
}

@Composable
fun VinoteNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val projectsViewModel: ProjectsViewModel = viewModel(factory = AppViewModelProvider.Factory)
    NavHost(
        navController = navController,
        startDestination = VinoteScreen.Projects.name,
        modifier = modifier
    ) {
        composable(route = VinoteScreen.Projects.name) {
            ProjectsScreen(
                onAddProject = { navController.navigate(VinoteScreen.AddProject.name) },
                onProjectClick = { navController.navigate(VinoteScreen.Editor.name) },
                onDashboardClick = { navController.navigate(VinoteScreen.Dashboard.name) }
            )
        }
        composable(route = VinoteScreen.AddProject.name) {
            AddProjectScreen(
                onNavigateUp = { navController.navigateUp() },
                onAddProject = { projectName ->
                    projectsViewModel.addProject(projectName)
                    navController.navigateUp()
                }
            )
        }
        composable(route = VinoteScreen.Editor.name) {
            EditorScreen(
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(route = VinoteScreen.Dashboard.name) {
            DashboardScreen()
        }
    }
}
