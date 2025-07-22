package com.example.vinote.ui.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinote.data.Chapter
import com.example.vinote.data.ChaptersRepository
import com.example.vinote.health.TypingSessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EditorViewModel(private val chaptersRepository: ChaptersRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(EditorUiState())
    val uiState: StateFlow<EditorUiState> = _uiState.asStateFlow()

    init {
        TypingSessionManager.startSession()
    }

    fun updateTitle(title: String) {
        _uiState.value = _uiState.value.copy(title = title)
    }

    fun updateContent(content: String) {
        val wordCount = content.split(Regex("\\s+")).count { it.isNotBlank() }
        _uiState.value = _uiState.value.copy(
            content = content,
            wordCount = wordCount
        )
    }

    fun saveChapter(projectId: Int) {
        TypingSessionManager.endSession()
        viewModelScope.launch {
            val chapter = Chapter(
                projectId = projectId,
                title = _uiState.value.title,
                content = _uiState.value.content,
                wordCount = _uiState.value.wordCount,
                timeSpentTyping = TypingSessionManager.typingTime.value,
                typingSpeed = 0f, // TODO: Recalculate typing speed
                order = 0 // Needs to be handled properly
            )
            chaptersRepository.insertChapter(chapter)
        }
    }

    override fun onCleared() {
        super.onCleared()
        TypingSessionManager.endSession()
    }
}

data class EditorUiState(
    val title: String = "",
    val content: String = "",
    val wordCount: Int = 0
)
