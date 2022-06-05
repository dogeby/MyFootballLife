package com.example.myfootballlife.data.youtubeapi.playlists

import com.google.gson.annotations.SerializedName

/** https://developers.google.com/youtube/v3/docs/playlists */
data class Thumbnails(
    @SerializedName("url") val url:String,
    @SerializedName("width") val width:UInt,
    @SerializedName("height") val height:UInt
)