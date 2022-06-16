package com.example.myfootballlife.data.youtubeapi.videos

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

/** https://developers.google.com/youtube/v3/docs/videos?hl=ko */
data class Statistics(
    @ColumnInfo(name = "view_count") @SerializedName("viewCount") val viewCount:Long
)