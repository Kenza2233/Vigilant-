package com.example.vinote.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinote.data.cloud.CloudProvider
import com.example.vinote.data.cloud.CloudSyncServiceFactory
import kotlinx.coroutines.launch

class SettingsViewModel(private val cloudSyncServiceFactory: CloudSyncServiceFactory) : ViewModel() {

    fun onSave(provider: CloudProvider) {
        viewModelScope.launch {
            val cloudSyncService = cloudSyncServiceFactory.create(provider)
            cloudSyncService.authenticate()
        }
    }
}
