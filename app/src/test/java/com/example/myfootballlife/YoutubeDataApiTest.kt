package com.example.myfootballlife

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
}