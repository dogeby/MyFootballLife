package com.example.myfootballlife.data.youtubeapi.channels

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

/** https://developers.google.com/youtube/v3/docs/channels#resource */
data class ContentDetails(
    @ColumnInfo(name = "related_playlists") @SerializedName("relatedPlaylists") val relatedPlaylists: Map<String, String>
)
