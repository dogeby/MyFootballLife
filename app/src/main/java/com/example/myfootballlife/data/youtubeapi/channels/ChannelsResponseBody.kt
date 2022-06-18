package com.example.myfootballlife.data.youtubeapi.channels

import com.example.myfootballlife.data.youtubeapi.PageInfo
import com.google.gson.annotations.SerializedName

/** https://developers.google.com/youtube/v3/docs/channels/list */
data class ChannelsResponseBody(
    @SerializedName("kind") val kind:String,
    @SerializedName("etag") val etag:String,
    @SerializedName("nextPageToken") val nextPageToken: String,
    @SerializedName("prevPageToken") val prevPageToken: String,
    @SerializedName("pageInfo") val pageInfo: PageInfo,
    @SerializedName("items") val items: List<Channels>?
)
