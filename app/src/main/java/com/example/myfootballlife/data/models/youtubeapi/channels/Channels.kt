package com.example.myfootballlife.data.models.youtubeapi.channels

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/** https://developers.google.com/youtube/v3/docs/channels#resource */
@Entity
data class Channels(
    @PrimaryKey val id: String = "",
    @Embedded val snippet: Snippet? = null,
    @Embedded val contentDetails: ContentDetails? = null
)
