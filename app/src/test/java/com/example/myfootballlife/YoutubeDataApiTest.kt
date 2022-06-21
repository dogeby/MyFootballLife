package com.example.myfootballlife

import com.example.myfootballlife.data.api.YoutubeDataApiModule
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class YoutubeDataApiTest {
    @Test
    fun testYoutubeDataApi() = runBlocking {
        // channel
        val channelResponseBody = YoutubeDataApiModule.provideYoutubeDataService().requestListChannels("zilioner83")
        val uploadsPlaylistId = channelResponseBody.items!![0].contentDetails.relatedPlaylists["uploads"]
        assertThat(channelResponseBody.items!![0].snippet.title, CoreMatchers.`is`("침착맨"))
        assertThat(uploadsPlaylistId, CoreMatchers.`is`("UUUj6rrhMTR9pipbAWBAMvUQ"))

        //playlist
        val playlistResponseBody = YoutubeDataApiModule.provideYoutubeDataService().requestListPlaylists(uploadsPlaylistId)
        assertThat(playlistResponseBody.items[0].snippet.title, CoreMatchers.`is`("Uploads from 침착맨"))

        //playlistItems
        val playlistItemResponseBody = YoutubeDataApiModule.provideYoutubeDataService().requestListPlaylistItems(null, uploadsPlaylistId)
        assertThat(playlistItemResponseBody.items!![0].snippet.playlistId, CoreMatchers.`is`(uploadsPlaylistId))

        //video
        val videoId = playlistItemResponseBody.items!![0].contentDetails.videoId
        val videoResponseBody = YoutubeDataApiModule.provideYoutubeDataService().requestListVideos(videoId)
        assertThat(videoResponseBody.items!![0].snippet.channelId, CoreMatchers.`is`(channelResponseBody.items!![0].id))
    }
}