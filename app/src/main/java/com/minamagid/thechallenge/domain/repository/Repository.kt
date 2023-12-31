package com.minamagid.thechallenge.domain.repository

import android.util.Log
import com.minamagid.thechallenge.domain.model.homeResponses.GeneralResponse
import com.minamagid.thechallenge.domain.model.homeResponses.Result
import com.minamagid.thechallenge.domain.model.search.SearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


interface Repository {
    suspend fun getMostViewedArticlesAtLastSevenDays(): GeneralResponse?
    suspend fun getMostSharedArticlesOnFacebook(): GeneralResponse?
    suspend fun getMostEmailedArticles(): GeneralResponse?

    suspend fun getSearch(query:String, page:Int): SearchResponse?

    suspend fun getArticles(): List<Result?>
    suspend fun deleteArticle(articleId: Long?)
    suspend fun insertArticle(result: Result?){
        Log.d("mina_insert","3")
    }
    suspend fun deleteLocalTable()
}