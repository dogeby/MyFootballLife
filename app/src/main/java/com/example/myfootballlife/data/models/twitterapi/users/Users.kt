package com.example.myfootballlife.data.models.twitterapi.users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/** https://developer.twitter.com/en/docs/twitter-api/data-dictionary/object-model/user */
@Entity
data class Users(
    @PrimaryKey val id:String = "",
    val name:String = "",
    val username:String = "",
    val description:String = "",
    @ColumnInfo(name = "profile_image_url") val profileImageUrl:String = ""
)