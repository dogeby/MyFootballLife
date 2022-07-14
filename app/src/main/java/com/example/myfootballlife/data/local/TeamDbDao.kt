package com.example.myfootballlife.data.local

import androidx.room.*
import com.example.myfootballlife.data.models.team.Team

@Dao
interface TeamDbDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(team: Team)

    @Delete
    suspend fun delete(team:Team)

    @Query("DELETE FROM Team WHERE name IN (:names)")
    suspend fun delete(names: List<String>)

    @Query("SELECT * FROM Team")
    suspend fun getAll():List<Team>

    @Query("SELECT name FROM Team WHERE NOT name IN (:names)")
    suspend fun getExcludeTeamNames(names: List<String>): List<String>
}