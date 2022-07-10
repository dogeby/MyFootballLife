package com.example.myfootballlife.data.local

import androidx.room.*
import com.example.myfootballlife.data.models.Subscription
import kotlinx.coroutines.flow.Flow

@Dao
interface SubscriptionDbDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubscription(subscription: Subscription)

    @Delete
    suspend fun deleteSubscription(subscription: Subscription)

    @Query("DELETE FROM Subscription WHERE id = :id")
    suspend fun deleteSubscription(id:String)

    @Query("SELECT * FROM Subscription")
    suspend fun getAllSubscription(): Flow<List<Subscription>>

    @Query("SELECT * FROM Subscription WHERE team = :teamName")
    suspend fun getSubscription(teamName: String): Flow<List<Subscription>>
}