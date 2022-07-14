package com.example.myfootballlife.data.local

import androidx.room.*
import com.example.myfootballlife.data.models.twitterapi.tweets.Tweets
import com.example.myfootballlife.data.models.twitterapi.users.Users
import kotlinx.coroutines.flow.Flow

@Dao
interface TwitterDbDao {
    /** Users **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: Users)

    @Delete
    suspend fun deleteUser(user: Users)

    @Query("DELETE FROM Users WHERE id IN (:ids)")
    suspend fun deleteUsers(ids: List<String>)

    @Query("DELETE FROM Users")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM Users")
    suspend fun getAllUser(): List<Users>

    @Query("SELECT * FROM Users WHERE id IN (:ids)")
    suspend fun getUsers(ids:List<String>): List<Users>

    @Query("SELECT id FROM Users WHERE NOT id IN (:ids)")
    suspend fun getExcludeUserIds(ids: List<String>): List<String>

    /** Tweets **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTweet(tweet: Tweets)

    @Delete
    suspend fun deleteTweet(tweet: Tweets)

    @Query("DELETE FROM Tweets WHERE id IN (:ids)")
    suspend fun deleteTweets(ids: List<String>)

    @Query("DELETE FROM Tweets WHERE NOT id IN (SELECT id FROM Tweets WHERE author_id == :authorId ORDER BY created_at DESC LIMIT :remainSize)")
    suspend fun deleteTweets(authorId: String, remainSize:Int)

    @Query("DELETE FROM Tweets WHERE author_id = :authorId")
    suspend fun deleteAllTweets(authorId: String)

    @Query("DELETE FROM Tweets")
    suspend fun deleteAllTweets()

    @Query("SELECT * FROM Tweets")
    suspend fun getAllTweet(): List<Tweets>

    @Query("SELECT * FROM Tweets WHERE author_id IN (:authorIds) ORDER BY created_at DESC")
    fun getTweetsByAuthorIds(authorIds: List<String>): Flow<List<Tweets>>

    @Query("SELECT * FROM Tweets WHERE author_id = :authorId ORDER BY created_at DESC")
    fun getTweetsByAuthorId(authorId: String): Flow<List<Tweets>>
}