package com.example.myfootballlife.data.twitterapi.timelines

import com.google.gson.annotations.SerializedName

/** https://developer.twitter.com/en/docs/twitter-api/tweets/timelines/api-reference/get-users-id-tweets#tab0 */
data class Meta(
    @SerializedName("count") val count:Int,
    @SerializedName("newest_id") val newestId:String?,
    @SerializedName("oldest_id") val oldestId:String?,
    @SerializedName("next_token") val nextToken:String?,
    @SerializedName("previous_token") val previousToken:String?
)