package com.minamagid.thechallenge.data.repository

import com.minamagid.thechallenge.common.Constants.API_KEY
import com.minamagid.thechallenge.data.local.NYTimesDB
import com.minamagid.thechallenge.domain.repository.Repository
import com.minamagid.thechallenge.data.remote.Api
import com.minamagid.thechallenge.domain.model.homeResponses.GeneralResponse
import com.minamagid.thechallenge.domain.model.homeResponses.Result
import com.minamagid.thechallenge.domain.model.search.SearchResponse
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: Api,private val db: NYTimesDB) : Repository {

    override suspend fun getMostViewedArticlesAtLastSevenDays(): GeneralResponse? {
        return api.getMostViewedArticles(API_KEY)
    }

    override suspend fun getMostSharedArticlesOnFacebook(): GeneralResponse? {
        return api.getMostSharedArticles(API_KEY)
    }

    override suspend fun getMostEmailedArticles(): GeneralResponse? {
        return api.getMostEmailedArticles(API_KEY)
    }

    override suspend fun getSearch(query: String, page: Int): SearchResponse? {
        return api.getSearchArticles(API_KEY,query,page)
    }


    //local
    override suspend fun insertArticle(article: Result?) {
        val x = db.localRoomDao.insert(article)

    }

    override suspend fun deleteLocalTable() {
        db.localRoomDao.clearTable()
    }

    override suspend fun deleteArticle(articleData: Long?) {
        db.localRoomDao.deleteItemLocalRoom(articleData?:0)
    }
    override suspend fun getArticles(): List<Result?> {
        return db.localRoomDao.getItemsFromLocalRoom()
    }

}