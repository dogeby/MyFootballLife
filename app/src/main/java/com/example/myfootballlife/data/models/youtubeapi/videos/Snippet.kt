package com.example.myfootballlife.data.models.youtubeapi.videos

import androidx.room.ColumnInfo
import com.example.myfootballlife.data.models.youtubeapi.thumbnails.Thumbnails

/** https://developers.google.com/youtube/v3/docs/videos?hl=ko */
data class Snippet(
    @ColumnInfo(name = "published_at") val publishedAt:String? = null,
    @ColumnInfo(name = "channel_id") val channelId:String? = null,
    val title:String? = null,
    val description:String? = null,
    val thumbnails:Map<String, Thumbnails>? = null,
    @ColumnInfo(name = "channel_title") val channelTitle:String? = null
)