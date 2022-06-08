package com.example.myfootballlife.data.twitterapi.users

import com.google.gson.annotations.SerializedName

/** https://developer.twitter.com/en/docs/twitter-api/data-dictionary/object-model/user */
data class Users(
    @SerializedName("id") val id:String,
    @SerializedName("name") val name:String,
    @SerializedName("username") val username:String,
    @SerializedName("description") val description:String,
    @SerializedName("profile_image_url") val profileImageUrl:String
)