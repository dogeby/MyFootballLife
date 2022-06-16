package com.example.myfootballlife.database

import androidx.room.*
import com.example.myfootballlife.data.Subscribe
import kotlinx.coroutines.flow.Flow

@Dao
interface SubscribeDbDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubscribe(subscribe: Subscribe)

    @Delete
    suspend fun deleteSubscribe(subscribe: Subscribe)

    @Query("SELECT * FROM Subscribe")
    fun getAllSubscribe(): Flow<List<Subscribe>>
}