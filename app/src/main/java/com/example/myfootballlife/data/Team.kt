package com.example.myfootballlife.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Team(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name:String
)
