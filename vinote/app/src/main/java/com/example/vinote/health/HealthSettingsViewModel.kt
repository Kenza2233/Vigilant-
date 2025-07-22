package com.example.vinote.health

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel

class HealthSettingsViewModel(private val context: Context) : ViewModel() {

    fun setHealthRemindersEnabled(enabled: Boolean) {
        if (enabled) {
            context.startService(Intent(context, HealthReminderService::class.java))
        } else {
            context.stopService(Intent(context, HealthReminderService::class.java))
        }
    }
}
