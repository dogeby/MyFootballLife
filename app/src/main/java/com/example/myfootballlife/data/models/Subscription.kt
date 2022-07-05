package com.example.myfootballlife.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subscription(
    @PrimaryKey val id: String,
    val kind: Kind,
    val team: String?
)