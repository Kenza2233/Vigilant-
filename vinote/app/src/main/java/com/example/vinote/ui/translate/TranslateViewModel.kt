package com.example.vinote.ui.translate

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TranslateViewModel : ViewModel() {
    private val _translatedText = MutableStateFlow("")
    val translatedText: StateFlow<String> = _translatedText.asStateFlow()

    fun translate(text: String) {
        // TODO: Implement translation logic
        _translatedText.value = "Translated: $text"
    }
}
