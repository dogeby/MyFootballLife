package com.example.myfootballlife.data.models.team

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Team(
    @PrimaryKey val name: String = "",
    val officialTwitterUserId: String? = null,
    val officialYoutubeChannelId: String? = null,
    val reporterTwitterUserId: List<String>? = null
)