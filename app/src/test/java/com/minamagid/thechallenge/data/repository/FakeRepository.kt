package com.minamagid.thechallenge.data.repository

import com.minamagid.thechallenge.domain.model.homeResponses.GeneralResponse
import com.minamagid.thechallenge.domain.model.homeResponses.Result
import com.minamagid.thechallenge.domain.model.search.SearchResponse
import com.minamagid.thechallenge.domain.repository.Repository

class FakeRepository : Repository {
    private val data = Result(
            0, "minaaaa", "", 0, null, "", emptyList(), 0, emptyList(), emptyList(), "", emptyList(),
    emptyList(), "", "", "", "", "", "", "", "","")
    private val response = "Success"
    private val cats = GeneralResponse( copyright="", numResults=2,
    results= listOf(data), status= response)


    override suspend fun getMostViewedArticlesAtLastSevenDays(): GeneralResponse? {
        return cats
    }

    override suspend fun getMostSharedArticlesOnFacebook(): GeneralResponse? {
        return cats
    }

    override suspend fun getMostEmailedArticles(): GeneralResponse? {
        return cats
    }

    override suspend fun getSearch(query: String, page: Int): SearchResponse? {
        return null
    }

    override suspend fun getArticles(): List<Result?> {
       return listOf(data)
    }

    override suspend fun deleteArticle(articleId: Long?) {
    }

    override suspend fun deleteLocalTable() {
    }
}