package com.example.myfootballlife.database

import androidx.room.*
import com.example.myfootballlife.data.youtubeapi.channels.Channels
import com.example.myfootballlife.data.youtubeapi.videos.Videos
import kotlinx.coroutines.flow.Flow

@Dao
interface YoutubeDbDao {
    /** Channels **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChannel(channel: Channels)

    @Delete
    suspend fun deleteChannel(channel: Channels)

    @Query("DELETE FROM channels WHERE id IN (:ids)")
    suspend fun deleteChannels(ids: List<String>)

    @Query("SELECT * FROM Channels")
    fun getAllChannel(): Flow<List<Channels>>

    @Query("SELECT * FROM Channels WHERE id IN (:ids)")
    fun getChannels(ids: List<String>): List<Channels>

    /** Videos **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo(video: Videos)

    @Delete
    suspend fun deleteVideo(video: Videos)

    @Query("DELETE FROM videos WHERE id IN (:ids)")
    suspend fun deleteVideos(ids: List<String>)

    @Query("DELETE FROM Videos WHERE NOT id IN (SELECT id FROM Videos WHERE channel_id == :channelId ORDER BY published_at DESC LIMIT :remainSize)")
    suspend fun deleteVideos(channelId: String, remainSize:Int)

    @Query("DELETE FROM Videos WHERE channel_id = :channelId")
    suspend fun deleteAllVideos(channelId:String)

    @Query("SELECT * FROM Videos")
    fun getAllVideo(): Flow<List<Videos>>

    @Query("SELECT * FROM Videos WHERE id IN (:ids)")
    fun getVideosByIds(ids: List<String>): List<Videos>

    @Query("SELECT * FROM Videos WHERE id = :id")
    fun getVideoById(id: String): Videos
}