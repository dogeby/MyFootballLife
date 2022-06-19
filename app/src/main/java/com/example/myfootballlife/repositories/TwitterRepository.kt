package com.example.myfootballlife.repositories

import com.example.myfootballlife.api.TwitterApiService
import com.example.myfootballlife.data.twitterapi.tweets.Tweets
import com.example.myfootballlife.data.twitterapi.users.Users
import com.example.myfootballlife.database.TwitterDbDao
import com.example.myfootballlife.utils.Converter
import javax.inject.Inject

class TwitterRepository @Inject constructor(
    private val twitterApiService: TwitterApiService,
    private val twitterDbDao: TwitterDbDao
    ) {

    /** get User **/
    suspend fun getUsers(ids: List<String>): List<Users> {
        val usersInDb = twitterDbDao.getUsers(ids)
        if(usersInDb.distinct().size == ids.distinct().size) return usersInDb

        val users = mutableListOf<Users>()
        users.addAll(usersInDb)
        val idsNotIncludedInDb = ids.filter { id -> usersInDb.find { it.id == id } == null }
        val commaSeparatedIdsString = Converter.stringListToCommaSeparatedString(idsNotIncludedInDb)
        twitterApiService.requestUsersByIds(commaSeparatedIdsString).users?.let {
            insertUsers(it)
            users.addAll(it)
        }
        return users.toList()
    }

    /** insert User **/
    private suspend fun insertUsers(users: List<Users>?) {
        users?.forEach { user -> twitterDbDao.insertUser(user) }
    }

    /** delete User **/
    suspend fun deleteUser(user: Users) = twitterDbDao.deleteUser(user)

    /** get Tweet **/
    suspend fun getTweets(authorId: String): List<Tweets> {
        val tweetsInDb = twitterDbDao.getTweetsByAuthorId(authorId)

        //DB에 요청한 작성자의 트윗이 없을 시
        if(tweetsInDb.isEmpty()) {
            val tweets = twitterApiService.requestUserTweetTimeline(authorId).tweets
            insertTweets(tweets)
            return tweets ?: emptyList()
        }

        //최신 트윗 확인
        val tweets = mutableListOf<Tweets>()
        var nextToken:String? = null
        do {
            val timelinesResponseBody = twitterApiService.requestUserTweetTimeline(authorId, nextToken, tweetsInDb[0].id)
            timelinesResponseBody.tweets?.let {
                insertTweets(it)
                tweets.addAll(it)
            }
            nextToken = timelinesResponseBody.meta.nextToken
        } while (nextToken != null)

        tweets.addAll(tweetsInDb)
        return tweets.toList()
    }

    suspend fun getOldTweets(authorId: String, untilTweetId:String):List<Tweets> {
        val tweets = twitterApiService.requestUserTweetTimeline(authorId, null, null, untilTweetId).tweets
        insertTweets(tweets)
        return tweets ?: emptyList()
    }

    /** insert Tweet **/
    private suspend fun insertTweets(tweets: List<Tweets>?) {
        tweets?.forEach { tweet -> twitterDbDao.insertTweet(tweet) }
    }
    /** delete Tweet **/
    suspend fun deleteTweets(authorId: String, remainSize:Int) = twitterDbDao.deleteTweets(authorId, remainSize)
}