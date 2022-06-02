package com.example.myfootballlife.request

import com.example.myfootballlife.data.youtubeapi.channels.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * YoutubeDataApi
 * https://developers.google.com/youtube/v3/docs
 */
interface YoutubeDataService {
    companion object {
        private const val apiKey = "AIzaSyAcgdr4Edx9dE-2o1hIsO7D74CPmYfxARY"
    }
    @GET("channels")
    suspend fun requestListChannels(@Query("part")part: String, @Query("forUsername") forUsername:String? = null, @Query("id") id:String? = null, @Query("key") key: String = apiKey):ResponseBody
}