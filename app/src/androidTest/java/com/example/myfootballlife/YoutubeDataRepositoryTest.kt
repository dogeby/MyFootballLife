package com.example.myfootballlife

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myfootballlife.data.youtubeapi.channels.RelatedPlaylistsKey
import com.example.myfootballlife.database.AppDatabase
import com.example.myfootballlife.repositories.YoutubeDataRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
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
class YoutubeDataRepositoryTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var appDatabase: AppDatabase
    @Inject
    lateinit var youtubeDataRepository: YoutubeDataRepository

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
    fun channelTest() = runBlocking {
        val channelIds =
            "UCEg25rdRZXg32iwai6N6l0w,UCpryVRk_VDudG8SHXgWcG0w,UCU2PacFf99vhb3hNiYDmxww," +
                    "UC6yW44UGJJBvYTlfC7CRg2Q,UCkzCjdRMrW2vXLx8mvPVLdQ,UC9LQwHZoucFT94I2h6JOcjw," +
                    "UCWV3obpZVGgJ3j9FVhEjF2Q,UC14UlmYlSNiQCBe9Eookf_A,UCLzKhsxrExAC6yAdtZ-BOWw,UCvXzEblUa0cfny4HAJ_ZOWw," +
                    "UCKcx1uK38H4AOkmfv4ywlrg,UCt9a_qP9CqHCNwilf-iULag"
        val channelsIdList = channelIds.split(',')
        val channels = youtubeDataRepository.getChannels(channelsIdList)
        MatcherAssert.assertThat(channels.size, CoreMatchers.`is`(channelsIdList.size))

        val emptyChannels = youtubeDataRepository.getChannels(emptyList())
        MatcherAssert.assertThat(emptyChannels.size, CoreMatchers.`is`(0))
    }

    @Test
    @Throws(Exception::class)
    fun videoTest() = runBlocking {
        val channelId = "UCEg25rdRZXg32iwai6N6l0w"
        val uploadPlaylistId = youtubeDataRepository.getChannels(listOf(channelId))[0].contentDetails.relatedPlaylists[RelatedPlaylistsKey.UPLOADS]!!
        val videos = youtubeDataRepository.getLatestVideos(uploadPlaylistId)
        val oldVideos1 = youtubeDataRepository.getOldVideos(uploadPlaylistId)
        val oldVideos2 = youtubeDataRepository.getOldVideos(uploadPlaylistId)
        MatcherAssert.assertThat(videos.size, CoreMatchers.`is`(5))
        MatcherAssert.assertThat(oldVideos1.size, CoreMatchers.`is`(5))
        MatcherAssert.assertThat(oldVideos2.size, CoreMatchers.`is`(5))


    }
}