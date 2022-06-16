package com.example.myfootballlife.database

import androidx.room.*
import com.example.myfootballlife.data.twitterapi.tweets.Tweets
import com.example.myfootballlife.data.twitterapi.users.Users
import kotlinx.coroutines.flow.Flow

@Dao
interface TwitterDbDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTweet(tweets: Tweets)

    @Delete
    suspend fun deleteTweet(tweets: Tweets)

    @Query("SELECT * FROM Tweets")
    fun getAllTweet(): Flow<List<Tweets>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(users: Users)

    @Delete
    suspend fun deleteUser(users: Users)

    @Query("SELECT * FROM Users")
    fun getAllUser(): Flow<List<Users>>

    @Query("SELECT * FROM Users WHERE id IN (:ids)")
    fun getUsers(ids:List<String>): Flow<List<Users>>
}