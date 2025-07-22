package com.example.vinote.data.cloud

import java.io.File

interface CloudSyncService {
    suspend fun authenticate()
    suspend fun uploadFile(file: File): String
    suspend fun downloadFile(fileId: String): File
    suspend fun getFileId(fileName: String): String?
}
