package com.example.vinote.health

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object TypingSessionManager {
    private val _typingTime = MutableStateFlow(0L)
    val typingTime: StateFlow<Long> = _typingTime.asStateFlow()

    private var sessionStartTime = 0L

    fun startSession() {
        if (sessionStartTime == 0L) {
            sessionStartTime = System.currentTimeMillis()
        }
    }

    fun endSession() {
        if (sessionStartTime != 0L) {
            val sessionDuration = System.currentTimeMillis() - sessionStartTime
            _typingTime.value += sessionDuration
            sessionStartTime = 0L
        }
    }

    fun reset() {
        _typingTime.value = 0L
        sessionStartTime = 0L
    }
}
