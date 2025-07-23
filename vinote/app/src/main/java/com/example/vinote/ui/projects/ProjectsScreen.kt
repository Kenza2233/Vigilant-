package com.example.vinote.ui.projects

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import com.example.vinote.ui.components.AnimatedSettingsIcon
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import com.example.vinote.navigation.VinoteScreen
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vinote.AppViewModelProvider
import com.example.vinote.R
import com.example.vinote.data.Project
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectsScreen(
    onAddProject: () -> Unit,
    onProjectClick: (Int) -> Unit,
    onDashboardClick: () -> Unit,
    onSettingsClick: () -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ProjectsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val projectsUiState by viewModel.projectsUiState.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                navController = navController,
                closeDrawer = { scope.launch { drawerState.close() } }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_name)) },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    actions = {
                        IconButton(onClick = onDashboardClick) {
                            Icon(Icons.Default.Dashboard, contentDescription = "Dashboard")
                        }
                        IconButton(onClick = onSettingsClick) {
                            AnimatedSettingsIcon()
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = onAddProject) {
                    Icon(Icons.Default.Add, contentDescription = "Add Project")
                }
            },
            modifier = modifier
        ) { innerPadding ->
            ProjectsBody(
                projectList = projectsUiState.projectList,
                onProjectClick = onProjectClick,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
private fun ProjectsBody(
    projectList: List<Project>,
    onProjectClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = projectList, key = { it.id }) { project ->
            ProjectListItem(project = project, onProjectClick = onProjectClick)
        }
    }
}

@Composable
private fun ProjectListItem(
    project: Project,
    onProjectClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    ListItem(
        headlineText = { Text(text = project.name) },
        modifier = modifier.clickable { onProjectClick(project.id) }
    )
}
