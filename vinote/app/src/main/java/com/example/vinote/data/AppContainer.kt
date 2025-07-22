package com.example.vinote.data

import android.content.Context

interface AppContainer {
    val projectsRepository: ProjectsRepository
    val chaptersRepository: ChaptersRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val projectsRepository: ProjectsRepository by lazy {
        ProjectsRepository(VinoteDatabase.getDatabase(context).projectDao())
    }

    override val chaptersRepository: ChaptersRepository by lazy {
        ChaptersRepository(VinoteDatabase.getDatabase(context).chapterDao())
    }
}
