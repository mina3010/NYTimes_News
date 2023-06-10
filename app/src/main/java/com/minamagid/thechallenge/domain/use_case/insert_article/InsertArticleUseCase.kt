package com.minamagid.thechallenge.domain.use_case.insert_article

import com.minamagid.thechallenge.common.Resource
import com.minamagid.thechallenge.domain.model.homeResponses.Result
import com.minamagid.thechallenge.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertArticleUseCase @Inject constructor(val repository: Repository) {
    operator fun invoke(
        article: Result
    ): Flow<Resource<*>> = flow {
        try {
            repository.insertArticle(article = article)
            emit(Resource.Success<Any>("Success"))
        } catch (e: Exception) {
            emit(Resource.Error<Any>(e.localizedMessage ?: "an Error Occurred"))
        }
    }
}