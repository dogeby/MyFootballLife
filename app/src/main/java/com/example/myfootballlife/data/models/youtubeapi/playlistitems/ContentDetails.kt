package com.example.myfootballlife.data.models.youtubeapi.playlistitems

import com.google.gson.annotations.SerializedName

/** https://developers.google.com/youtube/v3/docs/playlistItems?hl=ko */
data class ContentDetails(
    @SerializedName("videoId") val videoId:String
)