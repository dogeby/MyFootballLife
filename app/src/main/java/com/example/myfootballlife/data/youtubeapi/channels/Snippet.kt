package com.example.myfootballlife.data.youtubeapi.channels

import com.example.myfootballlife.data.youtubeapi.thumbnails.Thumbnails
import com.google.gson.annotations.SerializedName

/** https://developers.google.com/youtube/v3/docs/channels#resource */
data class Snippet(
    @SerializedName("title") val title:String,
    @SerializedName("description") val description:String,
    @SerializedName("thumbnails") val thumbnails: Map<String, Thumbnails>
)
