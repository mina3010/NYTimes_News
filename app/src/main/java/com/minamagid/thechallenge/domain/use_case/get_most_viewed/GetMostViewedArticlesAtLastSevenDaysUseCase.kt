package com.minamagid.thechallenge.domain.use_case.get_most_viewed

import com.minamagid.thechallenge.common.Resource
import com.minamagid.thechallenge.domain.model.homeResponses.GeneralResponse
import com.minamagid.thechallenge.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMostViewedArticlesAtLastSevenDaysUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(): Flow<Resource<GeneralResponse>> = flow {
        try {
            val movies = repository.getMostViewedArticlesAtLastSevenDays()
            emit(Resource.Success<GeneralResponse>(movies))
        } catch (e: HttpException) {
            emit(Resource.Error<GeneralResponse>(e.localizedMessage ?: "an Error Occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<GeneralResponse>("No Internet Connection, Check your Internet"))
        }
    }
}