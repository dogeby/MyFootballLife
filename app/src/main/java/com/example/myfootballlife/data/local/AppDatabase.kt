package com.example.myfootballlife.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myfootballlife.data.models.Subscription
import com.example.myfootballlife.data.models.twitterapi.tweets.Tweets
import com.example.myfootballlife.data.models.twitterapi.users.Users
import com.example.myfootballlife.data.models.youtubeapi.channels.Channels
import com.example.myfootballlife.data.models.youtubeapi.videos.Videos

@Database(entities = [Tweets::class, Users::class, Channels::class, Videos::class, Subscription::class], version = 1, exportSchema = false)
@TypeConverters(StringMapConverter::class, ThumbnailsMapConverter::class, KindConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun twitterDbDao(): TwitterDbDao
    abstract fun youtubeDbDao(): YoutubeDbDao
    abstract fun subscriptionDbDao(): SubscriptionDbDao
}