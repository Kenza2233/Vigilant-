package com.example.vinote.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinote.data.ChaptersRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DashboardViewModel(private val chaptersRepository: ChaptersRepository) : ViewModel() {

    val dashboardUiState: StateFlow<DashboardUiState> =
        chaptersRepository.getAllChaptersStream().map { chapters ->
            val totalWordCount = chapters.sumOf { it.wordCount }
            val totalTimeSpentTyping = chapters.sumOf { it.timeSpentTyping }
            val averageTypingSpeed = if (chapters.isNotEmpty()) {
                chapters.map { it.typingSpeed }.average().toFloat()
            } else {
                0f
            }
            DashboardUiState(
                totalWordCount = totalWordCount,
                totalTimeSpentTyping = totalTimeSpentTyping,
                averageTypingSpeed = averageTypingSpeed
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = DashboardUiState()
        )
}

data class DashboardUiState(
    val totalWordCount: Int = 0,
    val totalTimeSpentTyping: Long = 0L,
    val averageTypingSpeed: Float = 0f
)
