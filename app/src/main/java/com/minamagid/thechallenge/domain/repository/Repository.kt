package com.minamagid.thechallenge.domain.repository

import android.util.Log
import com.minamagid.thechallenge.domain.model.homeResponses.GeneralResponse
import com.minamagid.thechallenge.domain.model.homeResponses.Result
import com.minamagid.thechallenge.domain.model.search.SearchResponse


interface Repository {
    suspend fun getMostViewedArticlesAtLastSevenDays(): GeneralResponse?
    suspend fun getMostSharedArticlesOnFacebook(): GeneralResponse?
    suspend fun getMostEmailedArticles(): GeneralResponse?

    suspend fun getSearch(query:String, page:Int): SearchResponse?

    suspend fun getArticles(): List<Result?>
    suspend fun deleteArticle(articleId: Long?)
    suspend fun insertArticle(article: Result?){
        Log.d("RoomInsert", "Insert operation successful: $article")
    }
    suspend fun deleteLocalTable()
}