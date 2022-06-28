package com.example.myfootballlife.repositories

import com.example.myfootballlife.data.api.FirebaseRealtimeDbDao
import com.example.myfootballlife.data.local.TwitterDbDao
import com.example.myfootballlife.data.models.twitterapi.TweetsBody
import com.example.myfootballlife.data.models.twitterapi.UsersBody
import com.example.myfootballlife.data.models.twitterapi.tweets.Tweets
import com.example.myfootballlife.data.models.twitterapi.users.Users
import javax.inject.Inject

class TwitterRepository @Inject constructor(
    private val firebaseRealtimeDbDao: FirebaseRealtimeDbDao,
    private val twitterDbDao: TwitterDbDao
){
    /** Users **/
    suspend fun insertUsers(users: List<Users>) {
        users.forEach {
            twitterDbDao.insertUser(it)
        }
    }
    // TODO: 삭제 한 유저 처리(유저, 관련 트윗) 구현

    fun setUsersEventListener(callback: (UsersBody) -> Unit) {
        firebaseRealtimeDbDao.setValueEventListener("twitterUsers", callback)
    }

    fun getUsers() = twitterDbDao.getAllUser()

    /** Tweets **/
    suspend fun insertTweets(tweets: List<Tweets>) {
        tweets.forEach {
            twitterDbDao.insertTweet(it)
        }
    }

    fun setTweetsEventListener(authorId:String, callback: (TweetsBody) -> Unit) {
        firebaseRealtimeDbDao.setValueEventListener("latestTweets/$authorId", callback)
    }

    fun getTweets() = twitterDbDao.getAllTweet()
}