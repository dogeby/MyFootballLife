package com.example.myfootballlife.request

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * YoutubeDataApi
 * https://developers.google.com/youtube/v3/docs
 */
interface YoutubeDataApiService {

    companion object {
        private const val apiKey = "AIzaSyAcgdr4Edx9dE-2o1hIsO7D74CPmYfxARY"
    }

    /** https://developers.google.com/youtube/v3/docs/channels/list **/
    @GET("channels")
    suspend fun requestListChannels(@Query("part")part: String, @Query("forUsername") forUsername:String? = null, @Query("id") id:String? = null, @Query("key") key: String = apiKey):
            com.example.myfootballlife.data.youtubeapi.channels.ResponseBody

    /** https://developers.google.com/youtube/v3/docs/playlists/list */
    @GET("playlists")
    suspend fun requestListPlaylists(@Query("part")part: String, @Query("id") id:String? = null, @Query("channelId") channelId:String? = null, @Query("key") key: String = apiKey):
            com.example.myfootballlife.data.youtubeapi.playlists.ResponseBody

    /** https://developers.google.com/youtube/v3/docs/playlistItems/list?hl=ko */
    @GET("playlistItems")
    suspend fun requestListPlaylistItems(@Query("part")part: String, @Query("id") id:String? = null, @Query("playlistId") playlistId:String? = null, @Query("key") key: String = apiKey):
            com.example.myfootballlife.data.youtubeapi.playlistitems.ResponseBody

    /** https://developers.google.com/youtube/v3/docs/videos/list?hl=ko */
    @GET("videos")
    suspend fun requestListVideos(@Query("part")part: String, @Query("id") id:String? = null, @Query("key") key: String = apiKey):
            com.example.myfootballlife.data.youtubeapi.videos.ResponseBody
}