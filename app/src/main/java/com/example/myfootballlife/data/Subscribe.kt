package com.example.myfootballlife.data

import androidx.room.Entity

@Entity(primaryKeys = ["id", "kind"])
data class Subscribe(
    val id: String,
    val kind: Kind,
    val team: String?
)