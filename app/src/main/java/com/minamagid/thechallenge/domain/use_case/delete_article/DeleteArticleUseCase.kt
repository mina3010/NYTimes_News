package com.minamagid.thechallenge.domain.use_case.delete_article
import com.minamagid.thechallenge.common.Resource
import com.minamagid.thechallenge.domain.repository.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteArticleUseCase @Inject constructor(val repository: Repository) {
    operator fun invoke(
        imdbID: Long
    ): Flow<Resource<*>> = flow {
        try {
            repository.deleteArticle(imdbID)
            emit(Resource.Success<Any>("Success"))
        } catch (e: Exception) {
            emit(Resource.Error<Any>(e.localizedMessage ?: "an Error Occurred"))
        }
    }
}