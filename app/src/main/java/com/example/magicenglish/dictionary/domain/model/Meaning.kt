package com.example.magicenglish.dictionary.domain.model


data class Meaning(
    val definitions: List<Definition>,
    val partOfSpeech: String
)