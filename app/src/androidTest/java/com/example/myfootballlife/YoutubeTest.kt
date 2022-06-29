package com.example.myfootballlife

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myfootballlife.data.local.AppDatabase
import com.example.myfootballlife.repositories.YoutubeRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
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
class YoutubeTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var appDatabase: AppDatabase
    @Inject
    lateinit var youtubeRepository: YoutubeRepository

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
    fun getChannels() = runBlocking {
        youtubeRepository.setChannelsEventListener {
            GlobalScope.launch(Dispatchers.IO) {
                youtubeRepository.updateChannels(it.channels!!)
                MatcherAssert.assertThat(youtubeRepository.getChannels().size, CoreMatchers.`is`(14))
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun getVideos() = runBlocking {
        getChannels()
        val channelId = "UCKcx1uK38H4AOkmfv4ywlrg"
        youtubeRepository.setVideosEventListener(channelId) {
            GlobalScope.launch(Dispatchers.IO) {
                youtubeRepository.insertVideos(it.videos!!)
                MatcherAssert.assertThat(youtubeRepository.getVideos(channelId).first()[0].snippet!!.channelId, CoreMatchers.`is`(channelId))
            }
        }
    }
}