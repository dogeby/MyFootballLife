package com.example.myfootballlife.data.youtubeapi.videos

import com.google.gson.annotations.SerializedName

/** https://developers.google.com/youtube/v3/docs/videos?hl=ko */
data class Videos(
    @SerializedName("id") val id:String,
    @SerializedName("snippet") val snippet:Snippet,
    @SerializedName("contentDetails") val contentDetails:ContentDetails,
    @SerializedName("statistics") val statistics:Statistics
)