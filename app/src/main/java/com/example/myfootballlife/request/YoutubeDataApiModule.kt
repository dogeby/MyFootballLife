package com.example.myfootballlife.request

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class YoutubeDataApiModule {
    companion object {
        private const val baseUrl = "https://www.googleapis.com/youtube/v3/"
    }
    @Singleton
    @Provides
    fun provideYoutubeDataService(): YoutubeDataApiService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YoutubeDataApiService::class.java)
    }
}