package com.example.myfootballlife.repositories

import com.example.myfootballlife.api.YoutubeDataApiService
import com.example.myfootballlife.data.youtubeapi.channels.Channels
import com.example.myfootballlife.data.youtubeapi.videos.Videos
import com.example.myfootballlife.database.YoutubeDbDao
import com.example.myfootballlife.utils.Converter
import javax.inject.Inject

class YoutubeDataRepository @Inject constructor(
    private val youtubeDataApiService: YoutubeDataApiService,
    private val youtubeDbDao: YoutubeDbDao
    ) {
    private val playlistItemsNextPageToken = HashMap<String, String>()

    /** get Channel **/
    suspend fun getChannels(ids: List<String>): List<Channels> {
        val channelsInDb = youtubeDbDao.getChannels(ids)
        if(channelsInDb.distinct().size == ids.distinct().size) return channelsInDb

        val channels = mutableListOf<Channels>()
        channels.addAll(channelsInDb)
        val idsNotIncludedInDb = ids.filter { id -> channelsInDb.find { it.id == id } == null}
        val commaSeparatedIdsString = Converter.stringListToCommaSeparatedString(idsNotIncludedInDb)

        var nextPageToken:String? = null
        do {
            val channelsResponseBody = youtubeDataApiService.requestListChannels(null, commaSeparatedIdsString, nextPageToken)
            channelsResponseBody.items?.let {
                insertChannels(it)
                channels.addAll(it)
            }
            nextPageToken = channelsResponseBody.nextPageToken
        } while (nextPageToken != null)

        return channels.toList()
    }

    /** insert Channel **/
    private suspend fun insertChannels(channels: List<Channels>?) {
        channels?.forEach { channel -> youtubeDbDao.insertChannel(channel) }
    }

    /** delete Channel **/
    suspend fun deleteChannel(channel: Channels) = youtubeDbDao.deleteChannel(channel)

    /** get Video **/
    private suspend fun getVideos(videoIds:List<String>): List<Videos> {
        val videosInDb = youtubeDbDao.getVideosByIds(videoIds)
        if(videosInDb.distinct().size == videoIds.distinct().size) return videosInDb

        val videos = mutableListOf<Videos>()
        videos.addAll(videosInDb)
        val idsNotIncludedInDb = videoIds.filter { videoId -> videosInDb.find { it.id == videoId } == null}
        val commaSeparatedIdsString = Converter.stringListToCommaSeparatedString(idsNotIncludedInDb)
        youtubeDataApiService.requestListVideos(commaSeparatedIdsString).items?.let {
            insertVideos(it)
            videos.addAll(it)
        }
        videos.sortWith(compareByDescending { it.snippet.publishedAt })
        return videos
    }

    suspend fun getLatestVideos(playlistId: String): List<Videos> {
        val latestPlaylistItems = youtubeDataApiService.requestListPlaylistItems(null, playlistId)
        latestPlaylistItems.items?.let {  playlistItems ->
            val videoIds = playlistItems.map { playlistItem -> playlistItem.contentDetails.videoId }
            val videos = getVideos(videoIds)
            latestPlaylistItems.nextPageToken?.let { nextPageToken -> playlistItemsNextPageToken.put(playlistId, nextPageToken) }
            return videos
        }
        return emptyList()
    }

    suspend fun getOldVideos(playlistId: String): List<Videos> {
        if(!playlistItemsNextPageToken.containsKey(playlistId)) return emptyList()
        val nextPageToken = playlistItemsNextPageToken[playlistId]
        val nextPlaylistItems = youtubeDataApiService.requestListPlaylistItems(null, playlistId, nextPageToken)
        nextPlaylistItems.items?.let { playlistItems ->
            val videoIds = playlistItems.map { playlistItem -> playlistItem.contentDetails.videoId }
            val videos = getVideos(videoIds)
            nextPlaylistItems.nextPageToken?.let { nextPageToken -> playlistItemsNextPageToken.replace(playlistId, nextPageToken) }
            return videos
        }
        return emptyList()
    }

    /** insert Video **/
    private suspend fun insertVideos(videos: List<Videos>?) {
        videos?.forEach { video -> youtubeDbDao.insertVideo(video) }
    }

    /** delete Video **/
    suspend fun deleteVideos(channelId: String, remainSize:Int) = youtubeDbDao.deleteVideos(channelId, remainSize)

    suspend fun deleteAllVideos(channelId: String) = youtubeDbDao.deleteAllVideos(channelId)
}