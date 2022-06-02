package com.example.myfootballlife.data.youtubeapi.channels

import com.google.gson.annotations.SerializedName

/** https://developers.google.com/youtube/v3/docs/channels#resource */
data class Snippet(
    @SerializedName("title") val title:String,
    @SerializedName("description") val description:String,
)
