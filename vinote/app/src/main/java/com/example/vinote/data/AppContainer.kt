package com.example.vinote.data

import android.content.Context
import com.example.vinote.data.cloud.CloudSyncServiceFactory

interface AppContainer {
    val projectsRepository: ProjectsRepository
    val chaptersRepository: ChaptersRepository
    val cloudSyncServiceFactory: CloudSyncServiceFactory
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val projectsRepository: ProjectsRepository by lazy {
        ProjectsRepository(VinoteDatabase.getDatabase(context).projectDao())
    }

    override val chaptersRepository: ChaptersRepository by lazy {
        ChaptersRepository(VinoteDatabase.getDatabase(context).chapterDao())
    }

    override val cloudSyncServiceFactory: CloudSyncServiceFactory by lazy {
        CloudSyncServiceFactory(context)
    }
}
