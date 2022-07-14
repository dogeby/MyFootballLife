package com.example.myfootballlife.repositories

import com.example.myfootballlife.data.api.FirebaseRealtimeDbDao
import com.example.myfootballlife.data.local.YoutubeDbDao
import com.example.myfootballlife.data.models.youtubeapi.ChannelsBody
import com.example.myfootballlife.data.models.youtubeapi.VideosBody
import com.example.myfootballlife.data.models.youtubeapi.channels.Channels
import com.example.myfootballlife.data.models.youtubeapi.videos.Videos
import javax.inject.Inject

class YoutubeRepository @Inject constructor(
    private val firebaseRealtimeDbDao: FirebaseRealtimeDbDao,
    private val youtubeDbDao: YoutubeDbDao
){
    /** Channels **/
    suspend fun updateChannels(channels: List<Channels>) {
        insertChannels(channels)
        val excludeChannelIds = channels.map { channel -> channel.id }.let {channelIds -> youtubeDbDao.getExcludeChannelIds(channelIds)}
        youtubeDbDao.deleteChannels(excludeChannelIds)
    }

    private suspend fun insertChannels(channels: List<Channels>) {
        channels.forEach {
            youtubeDbDao.insertChannel(it)
        }
    }

    fun setChannelsEventListener(callback: (ChannelsBody) -> Unit) {
        firebaseRealtimeDbDao.setValueEventListener("youtubeChannels", callback)
    }

    suspend fun getChannels() = youtubeDbDao.getAllChannel()

    /** Videos **/
    suspend fun insertVideos(videos: List<Videos>) {
        videos.forEach {
            youtubeDbDao.insertVideo(it)
        }
    }

    fun setVideosEventListener(channelId: String, callback: (VideosBody) -> Unit) {
        firebaseRealtimeDbDao.setValueEventListener("latestVideos/$channelId", callback)
    }

    suspend fun getVideos() = youtubeDbDao.getAllVideo()

    fun getVideos(channelId: String) = youtubeDbDao.getVideosByChannelId(channelId)

    fun getVideos(channelIds: List<String>) = youtubeDbDao.getVideosByChannelIds(channelIds)

    suspend fun deleteVideo(video: Videos) = youtubeDbDao.deleteVideo(video)

    suspend fun deleteAllVideos(channelId: String) = youtubeDbDao.deleteAllVideos(channelId)

    suspend fun deleteVideos(channelId: String, remainSize:Int) = youtubeDbDao.deleteVideos(channelId, remainSize)

    suspend fun deleteAllVideos() = youtubeDbDao.deleteAllVideos()
}