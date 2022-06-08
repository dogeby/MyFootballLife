package com.example.myfootballlife.data.youtubeapi.videos

import com.example.myfootballlife.data.youtubeapi.PageInfo
import com.google.gson.annotations.SerializedName

/** https://developers.google.com/youtube/v3/docs/videos/list?hl=ko */
data class ResponseBody(
    @SerializedName("kind") val kind:String,
    @SerializedName("etag") val etag:String,
    @SerializedName("nextPageToken") val nextPageToken: String,
    @SerializedName("prevPageToken") val prevPageToken: String,
    @SerializedName("pageInfo") val pageInfo: PageInfo,
    @SerializedName("items") val items: List<Videos>
)