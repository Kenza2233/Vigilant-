package com.example.vinote

import android.app.Application
import com.example.vinote.data.AppContainer
import com.example.vinote.data.AppDataContainer

class VinoteApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
