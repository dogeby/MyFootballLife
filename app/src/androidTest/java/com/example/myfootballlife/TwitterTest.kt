package com.example.myfootballlife

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myfootballlife.data.local.AppDatabase
import com.example.myfootballlife.repositories.TwitterRepository
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
class TwitterTest {
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
    fun getUsers() = runBlocking{
        twitterRepository.setUsersEventListener {
            GlobalScope.launch {
                twitterRepository.updateUsers(it.users)
                MatcherAssert.assertThat(twitterRepository.getUsers().size, CoreMatchers.`is`(14))
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun getTweets() = runBlocking {
        getUsers()
        val userId = twitterRepository.getUsers().first().id
        twitterRepository.setTweetsEventListener(userId) {
            GlobalScope.launch(Dispatchers.IO) {
                twitterRepository.insertTweets(it.tweets)
                MatcherAssert.assertThat(twitterRepository.getTweets(userId).first()[0].authorId, CoreMatchers.`is`(userId))
            }
        }
    }
}