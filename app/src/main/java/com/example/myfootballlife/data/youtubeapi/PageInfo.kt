package com.example.myfootballlife.data.youtubeapi

import com.google.gson.annotations.SerializedName

/**
 * PageInfo: 결과 집합의 페이지 정보를 요약
 * totalResults: 결과 집합의 총 결과 수
 * resultsPerPage: API 응답에 포함된 결과 수
 */
data class PageInfo(
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("resultsPerPage") val resultsPerPage:Int
)
