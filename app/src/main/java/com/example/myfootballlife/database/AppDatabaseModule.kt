package com.example.myfootballlife.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppDatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase( @ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_database").build()

    @Singleton
    @Provides
    fun provideTwitterDbDao(appDatabase: AppDatabase) = appDatabase.twitterDbDao()

    @Singleton
    @Provides
    fun provideYoutubeDbDao(appDatabase: AppDatabase) = appDatabase.youtubeDbDao()

    @Singleton
    @Provides
    fun provideSubscriptionDbDao(appDatabase: AppDatabase) = appDatabase.subscriptionDbDao()
}