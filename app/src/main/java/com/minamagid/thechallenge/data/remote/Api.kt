package com.minamagid.thechallenge.data.remote

import com.minamagid.thechallenge.domain.model.homeResponses.GeneralResponse
import com.minamagid.thechallenge.domain.model.search.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {
    @GET("mostpopular/v2/viewed/7.json")
    suspend fun getMostViewedArticles(
        @Query("api-key") api_key: String
    ): GeneralResponse?

    @GET("mostpopular/v2/shared/1/facebook.json")
    suspend fun getMostSharedArticles(
        @Query("api-key") api_key: String
    ): GeneralResponse?

    @GET("mostpopular/v2/emailed/1.json")
    suspend fun getMostEmailedArticles(
        @Query("api-key") api_key: String
    ): GeneralResponse?

    @GET("search/v2/articlesearch.json")
    suspend fun getSearchArticles(
        @Query("api-key") api_key: String,
        @Query("q") query: String,
        @Query("page") page: Int,
    ): SearchResponse?

}