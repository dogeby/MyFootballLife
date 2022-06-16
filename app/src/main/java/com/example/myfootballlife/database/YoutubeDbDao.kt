package com.example.myfootballlife.database

import androidx.room.*
import com.example.myfootballlife.data.youtubeapi.channels.Channels
import com.example.myfootballlife.data.youtubeapi.videos.Videos
import kotlinx.coroutines.flow.Flow

@Dao
interface YoutubeDbDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChannel(channel: Channels)

    @Delete
    suspend fun deleteChannel(channel: Channels)

    @Query("SELECT * FROM Channels")
    fun getAllChannel(): Flow<List<Channels>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo(video: Videos)

    @Delete
    suspend fun deleteVideo(video: Videos)

    @Query("SELECT * FROM Videos")
    fun getAllVideo(): Flow<List<Videos>>
}