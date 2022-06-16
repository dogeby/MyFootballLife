package com.example.myfootballlife.data.youtubeapi.playlistitems

import com.example.myfootballlife.data.youtubeapi.PageInfo
import com.google.gson.annotations.SerializedName

/** https://developers.google.com/youtube/v3/docs/playlistItems/list?hl=ko */
data class PlaylistItemsResponseBody(
    @SerializedName("kind") val kind:String,
    @SerializedName("etag") val etag:String,
    @SerializedName("nextPageToken") val nextPageToken: String?,
    @SerializedName("prevPageToken") val prevPageToken: String?,
    @SerializedName("pageInfo") val pageInfo: PageInfo,
    @SerializedName("items") val items: List<PlaylistItems>
)