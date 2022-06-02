package com.example.myfootballlife.data.youtubeapi.channels

import com.google.gson.annotations.SerializedName

/** https://developers.google.com/youtube/v3/docs/channels#resource */
data class ContentDetails(
    @SerializedName("relatedPlaylists") val relatedPlaylists: Map<String, String>
)
