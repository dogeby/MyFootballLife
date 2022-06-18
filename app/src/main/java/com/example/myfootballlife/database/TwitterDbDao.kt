package com.example.myfootballlife.database

import androidx.room.*
import com.example.myfootballlife.data.twitterapi.tweets.Tweets
import com.example.myfootballlife.data.twitterapi.users.Users
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

    @Query("SELECT * FROM Users")
    fun getAllUser(): Flow<List<Users>>

    @Query("SELECT * FROM Users WHERE id IN (:ids)")
    fun getUsers(ids:List<String>): List<Users>

    /** Tweets **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTweet(tweet: Tweets)

    @Delete
    suspend fun deleteTweet(tweet: Tweets)

    @Query("DELETE FROM Tweets WHERE id IN (:ids)")
    suspend fun deleteTweets(ids: List<String>)

    @Query("DELETE FROM Tweets WHERE NOT id IN (SELECT id FROM Tweets WHERE author_id == :authorId ORDER BY created_at DESC LIMIT :remainSize)")
    suspend fun deleteTweets(authorId: String, remainSize:Int)

    @Query("SELECT * FROM Tweets")
    fun getAllTweet(): Flow<List<Tweets>>

    @Query("SELECT * FROM Tweets WHERE author_id IN (:authorIds) ORDER BY created_at DESC")
    fun getTweetsByAuthorIds(authorIds: List<String>): List<Tweets>

    @Query("SELECT * FROM Tweets WHERE author_id = :authorId ORDER BY created_at DESC")
    fun getTweetsByAuthorId(authorId: String): List<Tweets>
}