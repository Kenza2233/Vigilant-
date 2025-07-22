package com.example.vinote.ui.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinote.data.Chapter
import com.example.vinote.data.ChaptersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EditorViewModel(private val chaptersRepository: ChaptersRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(EditorUiState())
    val uiState: StateFlow<EditorUiState> = _uiState.asStateFlow()

    private var lastTypingEventTime = 0L

    fun updateTitle(title: String) {
        _uiState.value = _uiState.value.copy(title = title)
    }

    fun updateContent(content: String) {
        val currentTime = System.currentTimeMillis()
        val timeSpent = if (lastTypingEventTime != 0L) {
            currentTime - lastTypingEventTime
        } else {
            0L
        }
        lastTypingEventTime = currentTime

        val wordCount = content.split(Regex("\\s+")).count { it.isNotBlank() }
        val timeInMinutes = (_uiState.value.timeSpentTyping + timeSpent) / 60000f
        val typingSpeed = if (timeInMinutes > 0) {
            wordCount / timeInMinutes
        } else {
            0f
        }

        _uiState.value = _uiState.value.copy(
            content = content,
            wordCount = wordCount,
            timeSpentTyping = _uiState.value.timeSpentTyping + timeSpent,
            typingSpeed = typingSpeed
        )
    }

    fun saveChapter(projectId: Int) {
        viewModelScope.launch {
            val chapter = Chapter(
                projectId = projectId,
                title = _uiState.value.title,
                content = _uiState.value.content,
                wordCount = _uiState.value.wordCount,
                timeSpentTyping = _uiState.value.timeSpentTyping,
                typingSpeed = _uiState.value.typingSpeed,
                order = 0 // Needs to be handled properly
            )
            chaptersRepository.insertChapter(chapter)
        }
    }
}

data class EditorUiState(
    val title: String = "",
    val content: String = "",
    val wordCount: Int = 0,
    val timeSpentTyping: Long = 0L,
    val typingSpeed: Float = 0f
)
