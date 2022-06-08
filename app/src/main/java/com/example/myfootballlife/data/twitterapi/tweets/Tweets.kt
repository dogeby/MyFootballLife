package com.example.myfootballlife.data.twitterapi.tweets

import com.google.gson.annotations.SerializedName

/** https://developer.twitter.com/en/docs/twitter-api/data-dictionary/object-model/tweet */
data class Tweets(
    @SerializedName("id") val id:String,
    @SerializedName("text") val text:String,
    @SerializedName("author_id") val authorId:String,
    @SerializedName("created_at") val createdAt:String,
    @SerializedName("lang") val lang:String
)