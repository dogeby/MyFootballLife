package com.example.myfootballlife.data.models

import androidx.room.Entity

@Entity(primaryKeys = ["id", "kind"])
data class Subscription(
    val id: String,
    val kind: Kind,
    val team: String?
)