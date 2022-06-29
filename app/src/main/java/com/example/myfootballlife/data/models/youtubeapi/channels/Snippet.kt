package com.example.myfootballlife.data.models.youtubeapi.channels

import com.example.myfootballlife.data.models.youtubeapi.thumbnails.Thumbnails

/** https://developers.google.com/youtube/v3/docs/channels#resource */
data class Snippet(
    val title:String? = null,
    val description:String? = null,
    val thumbnails: Map<String, Thumbnails>? = null
)
