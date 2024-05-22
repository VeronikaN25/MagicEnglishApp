package com.example.magicenglish.test_trainer.di

import com.example.magicenglish.test_trainer.network.QuestionApi
import com.example.magicenglish.test_trainer.repository.QuestionRepository
import com.example.magicenglish.test_trainer.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//зависимости для внедрения в приложение
@Module
//установка модуля
@InstallIn(SingletonComponent::class)
object AppModule {

    //один экземпляр
    @Singleton
    @Provides
    fun provideQuestionApi(): QuestionApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuestionApi::class.java)
    }

    //промеж звено
    @Singleton
    @Provides
    fun provideQuestionRepository(api: QuestionApi) = QuestionRepository(api)
}