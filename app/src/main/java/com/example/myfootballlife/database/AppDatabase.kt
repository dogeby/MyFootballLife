package com.example.myfootballlife.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myfootballlife.data.Team

@Database(entities = [Team::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteTeamDbDao():FavoriteTeamDbDao
}