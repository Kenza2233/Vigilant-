package com.example.vinote.data

import kotlinx.coroutines.flow.Flow

class ChaptersRepository(private val chapterDao: ChapterDao) {
    fun getChaptersForProjectStream(projectId: Int): Flow<List<Chapter>> =
        chapterDao.getChaptersForProject(projectId)

    fun getAllChaptersStream(): Flow<List<Chapter>> = chapterDao.getAllChapters()

    suspend fun insertChapter(chapter: Chapter) = chapterDao.insert(chapter)

    suspend fun deleteChapter(chapter: Chapter) = chapterDao.delete(chapter)

    suspend fun updateChapter(chapter: Chapter) = chapterDao.update(chapter)
}
