package com.example.myfootballlife.data.youtubeapi.channels

import com.google.gson.annotations.SerializedName

/** https://developers.google.com/youtube/v3/docs/channels#resource */
data class Channels(
    @SerializedName("id") val id: String,
    @SerializedName("snippet") val snippet:Snippet,
    @SerializedName("contentDetails") val contentDetails:ContentDetails
)
