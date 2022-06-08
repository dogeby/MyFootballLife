package com.example.myfootballlife.data.twitterapi.timelines

import com.example.myfootballlife.data.twitterapi.tweets.Tweets
import com.google.gson.annotations.SerializedName

/** https://developer.twitter.com/en/docs/twitter-api/tweets/timelines/api-reference/get-users-id-tweets#tab0 */
data class TimelinesResponseBody(
    @SerializedName("data") val tweets: List<Tweets>
)