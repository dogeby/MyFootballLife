package com.example.myfootballlife.data.twitterapi.users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/** https://developer.twitter.com/en/docs/twitter-api/data-dictionary/object-model/user */
@Entity
data class Users(
    @PrimaryKey @SerializedName("id") val id:String,
    @SerializedName("name") val name:String,
    @SerializedName("username") val username:String,
    @SerializedName("description") val description:String,
    @ColumnInfo(name = "profile_image_url") @SerializedName("profile_image_url") val profileImageUrl:String
)