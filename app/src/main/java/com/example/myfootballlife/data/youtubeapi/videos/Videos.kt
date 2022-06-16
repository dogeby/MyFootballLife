package com.example.myfootballlife.data.youtubeapi.videos

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/** https://developers.google.com/youtube/v3/docs/videos?hl=ko */
@Entity
data class Videos(
    @PrimaryKey @SerializedName("id") val id:String,
    @Embedded @SerializedName("snippet") val snippet:Snippet,
    @Embedded @SerializedName("contentDetails") val contentDetails:ContentDetails,
    @Embedded @SerializedName("statistics") val statistics:Statistics
)