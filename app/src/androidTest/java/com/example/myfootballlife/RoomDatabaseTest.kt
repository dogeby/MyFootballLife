package com.example.myfootballlife

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myfootballlife.data.Team
import com.example.myfootballlife.database.AppDatabase
import com.example.myfootballlife.database.FavoriteTeamDbDao
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class RoomDatabaseTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var favoriteTeamDbDao: FavoriteTeamDbDao
    @Inject
    lateinit var appDatabase: AppDatabase

    @Before
    fun init() {
        hiltRule.inject()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeTeamAndReadTeam() = runBlocking{
        val inputTeamName = "tot"
        val team = Team(inputTeamName)
        favoriteTeamDbDao.insert(team)
        val teamNames = favoriteTeamDbDao.getAll().first()
        MatcherAssert.assertThat(teamNames[0].name, CoreMatchers.`is`(inputTeamName))
    }

    @Test
    @Throws(Exception::class)
    fun writeTeamAndDeleteTeam() = runBlocking {
        val inputTeamName = "ars"
        val team = Team(inputTeamName)
        favoriteTeamDbDao.insert(team)
        val afterInsertSize = favoriteTeamDbDao.getAll().first().size
        MatcherAssert.assertThat(afterInsertSize, CoreMatchers.`is`(2))

        favoriteTeamDbDao.delete(team)
        val afterDeleteSize = favoriteTeamDbDao.getAll().first().size
        MatcherAssert.assertThat(afterDeleteSize, CoreMatchers.`is`(1))
    }
}