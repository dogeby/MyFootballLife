package com.example.myfootballlife

import com.example.myfootballlife.request.TwitterApiModule
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class TwitterApiTest {

    @Test
    fun testTimeline() = runBlocking {
        val retrofit = TwitterApiModule().provideTwitterApiService()
        val userResponseBody = retrofit.requestRetrieveUsersByUsernames("SpursOfficial")
        assertThat(userResponseBody.users[0].name, CoreMatchers.`is`("Tottenham Hotspur"))
        val timelineResponseBody = retrofit.requestUserTweetTimeline(userResponseBody.users[0].id)
        assertThat(timelineResponseBody.tweets[0].authorId, CoreMatchers.`is`(userResponseBody.users[0].id))
    }
}