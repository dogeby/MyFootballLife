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
    suspend fun updateUsers(users: List<Users>) {
        insertUsers(users)
        val excludeUserIds = users.map { user -> user.id }.let { userIds -> twitterDbDao.getExcludeUserIds(userIds) }
        twitterDbDao.deleteUsers(excludeUserIds)
    }

    private suspend fun insertUsers(users: List<Users>) {
        users.forEach {
            twitterDbDao.insertUser(it)
        }
    }

    fun setUsersEventListener(callback: (UsersBody) -> Unit) {
        firebaseRealtimeDbDao.setValueEventListener("twitterUsers", callback)
    }

    suspend fun getUsers() = twitterDbDao.getAllUser()

    suspend fun deleteAllUsers() = twitterDbDao.deleteAllUsers()

    /** Tweets **/
    suspend fun insertTweets(tweets: List<Tweets>) {
        tweets.forEach {
            twitterDbDao.insertTweet(it)
        }
    }

    fun setTweetsEventListener(authorId:String, callback: (TweetsBody) -> Unit) {
        firebaseRealtimeDbDao.setValueEventListener("latestTweets/$authorId", callback)
    }

    suspend fun setAllLatestTweetsSingleEventListener(callback: (TweetsBody) -> Unit) {
        getUsers().forEach {
            firebaseRealtimeDbDao.setListenerForSingleValueEvent("latestTweets/${it.id}", callback)
        }
    }

    suspend fun getTweets() = twitterDbDao.getAllTweet()

    fun getTweets(authorId: String) = twitterDbDao.getTweetsByAuthorId(authorId)

    fun getTweets(authorIds: List<String>) = twitterDbDao.getTweetsByAuthorIds(authorIds)

    suspend fun deleteTweet(tweet: Tweets) = twitterDbDao.deleteTweet(tweet)

    suspend fun deleteAllTweets(authorId: String) = twitterDbDao.deleteAllTweets(authorId)

    suspend fun deleteTweets(authorId: String, remainSize:Int) = twitterDbDao.deleteTweets(authorId, remainSize)

    suspend fun deleteAllTweets() = twitterDbDao.deleteAllTweets()
}