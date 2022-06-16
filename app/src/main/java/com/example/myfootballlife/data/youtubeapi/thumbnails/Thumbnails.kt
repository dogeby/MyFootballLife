package com.example.myfootballlife.data.youtubeapi.thumbnails

import com.google.gson.annotations.SerializedName

/** https://developers.google.com/youtube/v3/docs/thumbnails?hl=ko */
data class Thumbnails(
    @SerializedName("url") val url:String,
    @SerializedName("width") val width:Int,
    @SerializedName("height") val height:Int
)