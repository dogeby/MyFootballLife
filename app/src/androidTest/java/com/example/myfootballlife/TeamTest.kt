package com.example.myfootballlife

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myfootballlife.data.local.AppDatabase
import com.example.myfootballlife.repositories.TeamRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
class TeamTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var appDatabase: AppDatabase
    @Inject
    lateinit var teamRepository: TeamRepository

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
    fun getTeams() = runBlocking {
        teamRepository.setTeamListenerForSingleEvent {
            GlobalScope.launch {
                teamRepository.updateTeam(it.teams!!)
                MatcherAssert.assertThat(teamRepository.getTeams().size, CoreMatchers.`is`(13))
            }
        }
        delay(5000)
    }
}