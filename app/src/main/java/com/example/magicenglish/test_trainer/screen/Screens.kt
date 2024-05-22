package com.example.magicenglish.test_trainer.screen

enum class Screens {
    QuizHome,
    QuizEnd;

    companion object {
        fun fromRoute(route: String?): Screens =
            when (route?.substringBefore("/")) {
                QuizHome.name -> QuizHome
                QuizEnd.name -> QuizEnd
                null -> QuizHome
                else -> throw IllegalArgumentException("Route $route is not recoginzed.")
            }
    }
}