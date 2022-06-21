package com.example.myfootballlife.data.models.youtubeapi.videos

import androidx.room.ColumnInfo
import com.example.myfootballlife.data.models.youtubeapi.thumbnails.Thumbnails
import com.google.gson.annotations.SerializedName

/** https://developers.google.com/youtube/v3/docs/videos?hl=ko */
data class Snippet(
    @ColumnInfo(name = "published_at") @SerializedName("publishedAt") val publishedAt:String,
    @ColumnInfo(name = "channel_id") @SerializedName("channelId") val channelId:String,
    @SerializedName("title") val title:String,
    @SerializedName("description") val description:String,
    @SerializedName("thumbnails") val thumbnails:Map<String, Thumbnails>,
    @ColumnInfo(name = "channel_title") @SerializedName("channelTitle") val channelTitle:String
)