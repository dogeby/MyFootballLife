package com.example.myfootballlife.data.models.youtubeapi.videos

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/** https://developers.google.com/youtube/v3/docs/videos?hl=ko */
@Entity
data class Videos(
    @PrimaryKey val id:String = "",
    @Embedded val snippet: Snippet? = null,
    @Embedded val contentDetails: ContentDetails? = null,
    @Embedded val statistics: Statistics? = null
)