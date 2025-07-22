package com.example.vinote.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ChapterDao {
    @Insert
    suspend fun insert(chapter: Chapter)

    @Update
    suspend fun update(chapter: Chapter)

    @Delete
    suspend fun delete(chapter: Chapter)

    @Query("SELECT * FROM chapters WHERE projectId = :projectId ORDER BY `order` ASC")
    fun getChaptersForProject(projectId: Int): Flow<List<Chapter>>

    @Query("SELECT * FROM chapters")
    fun getAllChapters(): Flow<List<Chapter>>
}
