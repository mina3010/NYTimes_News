package com.minamagid.thechallenge.domain.use_case.get_search

import com.minamagid.thechallenge.common.Resource
import com.minamagid.thechallenge.domain.model.search.SearchResponse
import com.minamagid.thechallenge.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSearchUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(query:String ,page:Int): Flow<Resource<SearchResponse>> = flow {
        try {
            val articles = repository.getSearch(query,page)
            emit(Resource.Success<SearchResponse>(articles))
        } catch (e: HttpException) {
            emit(Resource.Error<SearchResponse>(e.localizedMessage ?: "an Error Occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<SearchResponse>("No Internet Connection, Check your Internet"))
        }
    }
}