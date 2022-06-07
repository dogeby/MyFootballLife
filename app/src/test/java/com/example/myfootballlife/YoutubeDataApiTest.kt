package com.example.myfootballlife

import com.example.myfootballlife.data.youtubeapi.thumbnails.ThumbnailsKey
import com.example.myfootballlife.request.YoutubeDataApiModule
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class YoutubeDataApiTest {
    @Test
    fun requestListChannels() = runBlocking{
        val channelsResponseBody = YoutubeDataApiModule().provideYoutubeDataService().requestListChannels("snippet, contentDetails", "zilioner83")
        assertThat(channelsResponseBody.items[0].snippet.title, CoreMatchers.`is`("침착맨"))
        assertThat(channelsResponseBody.items[0].contentDetails.relatedPlaylists["uploads"], CoreMatchers.`is`("UUUj6rrhMTR9pipbAWBAMvUQ"))
    }

    @Test
    fun requestListPlaylists() = runBlocking {
        val playlistsResponseBody = YoutubeDataApiModule().provideYoutubeDataService().requestListPlaylists("snippet", "UUUj6rrhMTR9pipbAWBAMvUQ")
        assertThat(playlistsResponseBody.items[0].snippet.title, CoreMatchers.`is`("Uploads from 침착맨"))
        assertThat(playlistsResponseBody.items[0].snippet.thumbnails[ThumbnailsKey.default]?.url, CoreMatchers.`is`("https://i.ytimg.com/vi/UxcF9XQDSpw/default.jpg"))   //썸네일 테스트, 다른 동영상 업로드시 실패 예상
    }
}