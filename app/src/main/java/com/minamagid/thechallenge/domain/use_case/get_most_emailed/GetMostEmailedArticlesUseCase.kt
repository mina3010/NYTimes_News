package com.minamagid.thechallenge.domain.use_case.get_most_emailed

import com.minamagid.thechallenge.common.Resource
import com.minamagid.thechallenge.domain.model.homeResponses.GeneralResponse
import com.minamagid.thechallenge.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMostEmailedArticlesUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke():
            Flow<Resource<GeneralResponse>> = flow {
        try {
            val details = repository.getMostSharedArticlesOnFacebook()
            emit(Resource.Success<GeneralResponse>(details))
        } catch (e: retrofit2.HttpException) {
            emit(Resource.Error<GeneralResponse>(e.localizedMessage ?: "an Error Occurred"))
        } catch (e: java.io.IOException) {
            emit(Resource.Error<GeneralResponse>("No Internet Connection, Check your Internet"))
        }

    }
}