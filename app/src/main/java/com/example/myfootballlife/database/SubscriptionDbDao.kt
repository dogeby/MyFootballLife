package com.example.myfootballlife.database

import androidx.room.*
import com.example.myfootballlife.data.Subscription
import kotlinx.coroutines.flow.Flow

@Dao
interface SubscriptionDbDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubscription(subscription: Subscription)

    @Delete
    suspend fun deleteSubscription(subscription: Subscription)

    @Query("SELECT * FROM Subscription")
    fun getAllSubscription(): Flow<List<Subscription>>
}