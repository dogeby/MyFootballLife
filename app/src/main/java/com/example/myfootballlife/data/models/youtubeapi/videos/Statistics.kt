package com.example.myfootballlife.data.models.youtubeapi.videos

import androidx.room.ColumnInfo

/** https://developers.google.com/youtube/v3/docs/videos?hl=ko */
data class Statistics(
    @ColumnInfo(name = "view_count") val viewCount:Long = 0L
)