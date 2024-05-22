package com.example.magicenglish.dictionary.domain.use_case


import com.example.magicenglish.core.Resource
import com.example.magicenglish.dictionary.domain.model.WordInfo
import com.example.magicenglish.dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfo(
    private val repository: WordInfoRepository
) {

    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if(word.isBlank()) {
            return flow {  }
        }
        return repository.getWordInfo(word)
    }
}