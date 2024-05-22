package com.example.magicenglish.test_trainer.network


import com.example.magicenglish.test_trainer.model.Question
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface QuestionApi {

    @GET("quizList.json")
    suspend fun getAllQuestions(): Question
}