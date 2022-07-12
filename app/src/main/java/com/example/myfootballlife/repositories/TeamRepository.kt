package com.example.myfootballlife.repositories

import com.example.myfootballlife.data.api.FirebaseRealtimeDbDao
import com.example.myfootballlife.data.local.TeamDbDao
import com.example.myfootballlife.data.models.team.Team
import com.example.myfootballlife.data.models.team.TeamBody
import javax.inject.Inject

class TeamRepository @Inject constructor(
    private val firebaseRealtimeDbDao: FirebaseRealtimeDbDao,
    private val teamDbDao: TeamDbDao
) {
    suspend fun updateTeam(teams: List<Team>) {
        insertTeams(teams)
        val excludeTeamNames = teams.map { team -> team.name }.let { names -> teamDbDao.getExcludeTeamNames(names) }
        teamDbDao.delete(excludeTeamNames)
    }
    private suspend fun insertTeams(teams: List<Team>) {
        teams.forEach {
            teamDbDao.insert(it)
        }
    }

    fun setTeamListenerForSingleEvent(callback: (TeamBody) -> Unit) {
        firebaseRealtimeDbDao.setListenerForSingleValueEvent("teamInfo", callback)
    }

    fun getTeams() = teamDbDao.getAll()
}