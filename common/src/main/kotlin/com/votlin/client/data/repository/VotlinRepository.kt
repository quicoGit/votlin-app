package com.votlin.client.domain.repository

import com.votlin.client.data.datasource.local.LocalDataSource
import com.votlin.client.data.datasource.remote.RemoteDataSource
import com.votlin.model.Rate
import com.votlin.model.Talk
import com.votlin.model.Track

class VotlinRepository(private val local: LocalDataSource,
                       private val remote: RemoteDataSource) : Repository {

    override suspend fun getTalks(): List<Talk> = remote.getTalks()

    override suspend fun getTalk(talkId: Int): Talk = remote.getTalk(talkId = talkId)

    override fun getFavoriteTalks(): List<Talk> = local.getFavoriteTalks()

    override fun getTrackTalks(track: Track): List<Talk> = remote.getTrackTalks(track)

    override fun rateTalk(rate: Rate) {
        remote.rateTalk(rate)
        local.saveRate(rate)
    }

    override fun saveTalk(talk: Talk) = local.saveTalk(talk)
}