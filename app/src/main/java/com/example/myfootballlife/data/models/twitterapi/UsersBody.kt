package com.example.myfootballlife.data.models.twitterapi

import com.example.myfootballlife.data.models.twitterapi.users.Users

data class UsersBody(
    val users: List<Users> = listOf()
)