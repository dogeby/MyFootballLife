package com.example.myfootballlife.data.models.twitterapi.users

import com.google.gson.annotations.SerializedName

/** https://developer.twitter.com/en/docs/twitter-api/users/lookup/api-reference/get-users-by-username-username */
data class UsersResponseBody(
    @SerializedName("data") val users: List<Users>?
)