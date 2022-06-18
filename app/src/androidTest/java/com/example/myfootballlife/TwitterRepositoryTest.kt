package com.example.myfootballlife

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myfootballlife.database.AppDatabase
import com.example.myfootballlife.repositories.TwitterRepository
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
class TwitterRepositoryTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var appDatabase: AppDatabase
    @Inject
    lateinit var twitterRepository: TwitterRepository

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
    fun userTest() = runBlocking {
        val uIds =
            "2244994945,6253282,1599057055,284660112,1193109572015329280,3157899825,330262748,799617898847281153,23925153,18841928,1199736038874324993,52057709"
        val uIdList = uIds.split(',')
        val users = twitterRepository.getUsers(uIdList)
        MatcherAssert.assertThat(users.size, CoreMatchers.`is`(uIdList.size))

        val emptyUsers = twitterRepository.getUsers(emptyList())
        MatcherAssert.assertThat(emptyUsers.size, CoreMatchers.`is`(0))
    }

    @Test
    @Throws(Exception::class)
    fun tweetsTest() = runBlocking {
        val uIdList = listOf("2244994945")
        val users = twitterRepository.getUsers(uIdList)
        val user = users[0]
        val tweets = twitterRepository.getTweets(user.id)
        MatcherAssert.assertThat(tweets[0].authorId, CoreMatchers.`is`(user.id))

        val oldTweets = twitterRepository.getOldTweets(user.id, tweets.last().id)
        MatcherAssert.assertThat(oldTweets[0].authorId, CoreMatchers.`is`(user.id))

        twitterRepository.deleteTweets(user.id, 50)

        val afterDeleteTweets = twitterRepository.getTweets(user.id)
        MatcherAssert.assertThat(afterDeleteTweets.size, CoreMatchers.`is`(50))

        val emptyTweets = twitterRepository.getTweets("1111")
        MatcherAssert.assertThat(emptyTweets.size, CoreMatchers.`is`(0))
    }
}