package com.example.vinote.health

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vinote.AppViewModelProvider
import com.example.vinote.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthSettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: HealthSettingsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    var isEnabled by remember { mutableStateOf(false) }
    var reminderInterval by remember { mutableStateOf(30) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.health_settings)) }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Enable Health Reminders",
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = isEnabled,
                    onCheckedChange = {
                        isEnabled = it
                        viewModel.setHealthRemindersEnabled(it)
                    }
                )
            }
        }
    }
}
