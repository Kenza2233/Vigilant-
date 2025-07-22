package com.example.vinote.data.cloud

import android.content.Context

class CloudSyncServiceFactory(private val context: Context) {
    fun create(provider: CloudProvider): CloudSyncService {
        return when (provider) {
            CloudProvider.GOOGLE_DRIVE -> GoogleDriveService(context)
            CloudProvider.ONEDRIVE -> OneDriveService(context)
            CloudProvider.DROPBOX -> DropboxService(context)
            // TODO: Implement other cloud providers
            else -> throw IllegalArgumentException("Unsupported cloud provider: $provider")
        }
    }
}
