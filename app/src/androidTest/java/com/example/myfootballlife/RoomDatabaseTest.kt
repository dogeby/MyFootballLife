package com.example.myfootballlife

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myfootballlife.data.local.AppDatabase
import com.example.myfootballlife.data.local.SubscriptionDbDao
import com.example.myfootballlife.data.local.TwitterDbDao
import com.example.myfootballlife.data.local.YoutubeDbDao
import com.example.myfootballlife.data.models.Kind
import com.example.myfootballlife.data.models.Subscription
import com.example.myfootballlife.di.TwitterApiModule
import com.example.myfootballlife.di.YoutubeDataApiModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class RoomDatabaseTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var appDatabase: AppDatabase
    @Inject
    lateinit var twitterDbDao: TwitterDbDao
    @Inject
    lateinit var youtubeDbDao: YoutubeDbDao
    @Inject
    lateinit var subscriptionDbDao: SubscriptionDbDao

    @Before
    fun init() {
        hiltRule.inject()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeTwitterDb() = runBlocking {
        val retrofit = TwitterApiModule.provideTwitterApiService()
        val userResponseBody = retrofit.requestRetrieveUsersByUsernames("SpursOfficial")
        val user = userResponseBody.users!![0]
        twitterDbDao.insertUser(user)
        MatcherAssert.assertThat(twitterDbDao.getAllUser().first()[0].id, CoreMatchers.`is`(user.id))

        val timelineResponseBody = retrofit.requestUserTweetTimeline(user.id)
        twitterDbDao.insertTweet(timelineResponseBody.tweets!![0])
        MatcherAssert.assertThat(twitterDbDao.getAllTweet().first()[0].authorId, CoreMatchers.`is`(timelineResponseBody.tweets!![0].authorId))
    }

    @Test
    @Throws(Exception::class)
    fun writeYoutubeDb() = runBlocking {
        // channel
        val channelResponseBody = YoutubeDataApiModule.provideYoutubeDataService().requestListChannels("snippet, contentDetails", "zilioner83")
        youtubeDbDao.insertChannel(channelResponseBody.items!![0])
        val channelInDb = youtubeDbDao.getAllChannel().first()[0]
        MatcherAssert.assertThat(channelInDb.id, CoreMatchers.`is`(channelResponseBody.items!![0].id))
        val uploadsPlaylistId = channelResponseBody.items!![0].contentDetails.relatedPlaylists["uploads"]
        //playlistItems
        val playlistItemResponseBody = YoutubeDataApiModule.provideYoutubeDataService().requestListPlaylistItems("snippet, contentDetails", null, uploadsPlaylistId)
        //video
        val videoId = playlistItemResponseBody.items!![0].contentDetails.videoId
        val videoResponseBody = YoutubeDataApiModule.provideYoutubeDataService().requestListVideos("snippet, contentDetails, statistics", videoId)
        youtubeDbDao.insertVideo(videoResponseBody.items!![0])
        val videoInDb = youtubeDbDao.getAllVideo().first()[0]
        MatcherAssert.assertThat(videoInDb.id, CoreMatchers.`is`(videoId))
    }

    @Test
    @Throws(Exception::class)
    fun writeSubscriptionDb() = runBlocking {
        val subscribe = Subscription("1", Kind.TWITTER, "tot")
        subscriptionDbDao.insertSubscription(subscribe)
        val subscribes = subscriptionDbDao.getAllSubscription()
        MatcherAssert.assertThat(subscribes.first()[0].kind, CoreMatchers.`is`(subscribe.kind))
    }
}