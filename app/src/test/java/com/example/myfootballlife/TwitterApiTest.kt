package com.example.myfootballlife

import com.example.myfootballlife.di.TwitterApiModule
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class TwitterApiTest {

    @Test
    fun testTimeline() = runBlocking {
        val retrofit = TwitterApiModule.provideTwitterApiService()
        val userResponseBody = retrofit.requestRetrieveUsersByUsernames("SpursOfficial")
        val users = userResponseBody.users
        assertThat(users?.get(0)?.name, CoreMatchers.`is`("Tottenham Hotspur"))

        val timelineResponseBody = retrofit.requestUserTweetTimeline(users?.get(0)?.id ?: "")
        assertThat(timelineResponseBody.tweets!![0].authorId, CoreMatchers.`is`(users?.get(0)?.id ?: ""))
        val usersResponseBody = retrofit.requestUsersByIds(users?.get(0)?.id ?: "")
        assertThat(usersResponseBody.users?.get(0)?.name ?: "", CoreMatchers.`is`("Tottenham Hotspur"))
    }
}