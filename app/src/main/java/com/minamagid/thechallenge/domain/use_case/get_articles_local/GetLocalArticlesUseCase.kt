package com.minamagid.thechallenge.domain.use_case.get_articles_local

import com.minamagid.thechallenge.common.Resource
import com.minamagid.thechallenge.domain.model.homeResponses.Result
import com.minamagid.thechallenge.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetLocalArticlesUseCase @Inject constructor(val repository: Repository) {
    operator fun invoke(
    ): Flow<Resource<List<Result>>> = flow {
        try {
            val movies = repository.getArticles()
            emit(Resource.Success<List<Result>>(movies as List<Result>))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Result>>(e.localizedMessage ?: "an Error Occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Result>>("No Internet Connection, Check your Internet"))
        }
    }
}