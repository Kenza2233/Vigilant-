package com.example.vinote.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Project::class, Chapter::class], version = 1, exportSchema = false)
abstract class VinoteDatabase : RoomDatabase() {

    abstract fun projectDao(): ProjectDao
    abstract fun chapterDao(): ChapterDao

    companion object {
        @Volatile
        private var Instance: VinoteDatabase? = null

        fun getDatabase(context: Context): VinoteDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, VinoteDatabase::class.java, "vinote_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
