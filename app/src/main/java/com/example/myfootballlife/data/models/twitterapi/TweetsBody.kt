package com.example.myfootballlife.data.models.twitterapi

import com.example.myfootballlife.data.models.twitterapi.tweets.Tweets

data class TweetsBody(
    val tweets: List<Tweets>? = null
)