package com.example.myfootballlife.data.youtubeapi.channels

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/** https://developers.google.com/youtube/v3/docs/channels#resource */
@Entity
data class Channels(
    @PrimaryKey @SerializedName("id") val id: String,
    @Embedded @SerializedName("snippet") val snippet:Snippet,
    @Embedded @SerializedName("contentDetails") val contentDetails:ContentDetails
)
