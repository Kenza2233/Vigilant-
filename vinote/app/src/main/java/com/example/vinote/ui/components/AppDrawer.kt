package com.example.vinote.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.vinote.navigation.VinoteScreen

@Composable
fun AppDrawer(
    navController: NavController,
    closeDrawer: () -> Unit
) {
    ModalDrawerSheet {
        NavigationDrawerItem(
            label = { Text("Themes") },
            icon = { Icon(Icons.Default.Palette, contentDescription = "Themes") },
            selected = false,
            onClick = {
                navController.navigate(VinoteScreen.Theme.name)
                closeDrawer()
            }
        )
        NavigationDrawerItem(
            label = { Text("Settings") },
            icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
            selected = false,
            onClick = {
                navController.navigate(VinoteScreen.Settings.name)
                closeDrawer()
            }
        )
        NavigationDrawerItem(
            label = { Text("Health") },
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Health") },
            selected = false,
            onClick = {
                navController.navigate(VinoteScreen.Health.name)
                closeDrawer()
            }
        )
        NavigationDrawerItem(
            label = { Text("Translate") },
            icon = { Icon(Icons.Default.Translate, contentDescription = "Translate") },
            selected = false,
            onClick = {
                navController.navigate(VinoteScreen.Translate.name)
                closeDrawer()
            }
        )
        NavigationDrawerItem(
            label = { Text("Interface Settings") },
            icon = { Icon(Icons.Default.WbSunny, contentDescription = "Interface Settings") },
            selected = false,
            onClick = {
                navController.navigate(VinoteScreen.InterfaceSettings.name)
                closeDrawer()
            }
        )
        NavigationDrawerItem(
            label = { Text("About") },
            icon = { Icon(Icons.Default.Info, contentDescription = "About") },
            selected = false,
            onClick = {
                navController.navigate(VinoteScreen.About.name)
                closeDrawer()
            }
        )
        NavigationDrawerItem(
            label = { Text("New Features") },
            icon = { Icon(Icons.Default.Info, contentDescription = "New Features") },
            selected = false,
            onClick = {
                navController.navigate(VinoteScreen.NewFeatures.name)
                closeDrawer()
            }
        )
    }
}
