package com.example.myfootballlife.data.models.youtubeapi.channels

import androidx.room.ColumnInfo

/** https://developers.google.com/youtube/v3/docs/channels#resource */
data class ContentDetails(
    @ColumnInfo(name = "related_playlists") val relatedPlaylists: Map<String, String>? = null
)
