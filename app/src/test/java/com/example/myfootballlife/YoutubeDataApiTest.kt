package com.example.myfootballlife

import com.example.myfootballlife.request.YoutubeDataApiModule
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class YoutubeDataApiTest {
    @Test
    fun requestListChannels() = runBlocking{
        val responseBody = YoutubeDataApiModule().provideYoutubeDataService().requestListChannels("snippet", "zilioner83")
        assertThat(responseBody.items[0].snippet.title, CoreMatchers.`is`("침착맨"))
    }
}