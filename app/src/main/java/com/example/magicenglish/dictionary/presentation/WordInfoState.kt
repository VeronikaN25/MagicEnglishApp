package com.example.magicenglish.dictionary.presentation

import com.example.magicenglish.dictionary.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)