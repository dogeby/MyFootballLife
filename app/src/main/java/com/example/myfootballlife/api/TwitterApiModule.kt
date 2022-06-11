package com.example.myfootballlife.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TwitterApiModule {
    private const val baseUrl = "https://api.twitter.com/"
    @Singleton
    @Provides
    fun provideTwitterApiService(): TwitterApiService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TwitterApiService::class.java)
    }
}