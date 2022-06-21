package com.example.myfootballlife.data.models.youtubeapi.playlistitems

import com.google.gson.annotations.SerializedName

/** https://developers.google.com/youtube/v3/docs/playlistItems?hl=ko */
data class Snippet(
    @SerializedName("publishedAt") val publishedAt:String,
    @SerializedName("channelId") val channelId:String,
    @SerializedName("playlistId") val playlistId:String,
    @SerializedName("position") val position:UInt
)