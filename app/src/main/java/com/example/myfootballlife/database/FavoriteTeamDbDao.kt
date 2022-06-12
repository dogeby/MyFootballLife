package com.example.myfootballlife.database

import androidx.room.*
import com.example.myfootballlife.data.Team
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteTeamDbDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(team: Team)

    @Delete
    fun delete(team: Team)

    @Query("SELECT * FROM Team")
    fun getAll(): Flow<List<Team>>
}