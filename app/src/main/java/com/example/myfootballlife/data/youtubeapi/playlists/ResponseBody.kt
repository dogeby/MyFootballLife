package com.example.myfootballlife.data.youtubeapi.playlists

import com.example.myfootballlife.data.youtubeapi.PageInfo
import com.google.gson.annotations.SerializedName

data class ResponseBody(
    @SerializedName("kind") val kind:String,
    @SerializedName("etag") val etag:String,
    @SerializedName("nextPageToken") val nextPageToken: String,
    @SerializedName("prevPageToken") val prevPageToken: String,
    @SerializedName("pageInfo") val pageInfo: PageInfo,
    @SerializedName("items") val items: List<Playlists>
)