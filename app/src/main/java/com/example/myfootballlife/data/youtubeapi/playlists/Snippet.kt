package com.example.myfootballlife.data.youtubeapi.playlists

import com.google.gson.annotations.SerializedName

data class Snippet(
    @SerializedName("publishedAt") val publishedAt:String,
    @SerializedName("channelId") val channelId:String,
    @SerializedName("title") val title:String,
    @SerializedName("description") val description:String,
    @SerializedName("thumbnails") val thumbnails:Map<String, Thumbnails>,
    @SerializedName("channelTitle") val channelTitle:String
)