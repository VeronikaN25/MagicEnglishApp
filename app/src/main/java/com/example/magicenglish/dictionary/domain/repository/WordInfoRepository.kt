package com.example.magicenglish.dictionary.domain.repository

import com.example.magicenglish.core.Resource
import com.example.magicenglish.dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}