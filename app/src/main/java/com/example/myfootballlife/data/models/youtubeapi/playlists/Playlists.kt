package com.example.myfootballlife.data.models.youtubeapi.playlists

import com.google.gson.annotations.SerializedName

/** https://developers.google.com/youtube/v3/docs/playlists */
data class Playlists(
    @SerializedName("id") val id:String,
    @SerializedName("snippet") val snippet: Snippet
)