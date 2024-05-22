package com.example.magicenglish.test_trainer.screen

import androidx.compose.runtime.Composable

enum class Screens {
    QuizHome,
    QuizEnd,
    IntermediateResult;

    companion object {
        fun fromRoute(route: String?): Screens =
            when (route?.substringBefore("/")) {
                QuizHome.name -> QuizHome
                QuizEnd.name -> QuizEnd
                IntermediateResult.name -> IntermediateResult
                null -> QuizHome
                else -> throw IllegalArgumentException("Route $route is not recoginzed.")
            }
    }
}