package com.example.myfootballlife.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myfootballlife.data.Subscription
import com.example.myfootballlife.data.twitterapi.tweets.Tweets
import com.example.myfootballlife.data.twitterapi.users.Users
import com.example.myfootballlife.data.youtubeapi.channels.Channels
import com.example.myfootballlife.data.youtubeapi.videos.Videos

@Database(entities = [Tweets::class, Users::class, Channels::class, Videos::class, Subscription::class], version = 1, exportSchema = false)
@TypeConverters(StringMapConverter::class, ThumbnailsMapConverter::class, KindConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun twitterDbDao(): TwitterDbDao
    abstract fun youtubeDbDao(): YoutubeDbDao
    abstract fun subscriptionDbDao(): SubscriptionDbDao
}