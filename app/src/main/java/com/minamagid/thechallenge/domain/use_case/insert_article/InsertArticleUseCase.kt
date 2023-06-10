package com.minamagid.thechallenge.domain.use_case.insert_article

import android.util.Log
import com.minamagid.thechallenge.common.Resource
import com.minamagid.thechallenge.domain.model.homeResponses.Result
import com.minamagid.thechallenge.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertArticleUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(article: Result
    ): Flow<Resource<*>> = flow {
        try {
            Log.d("mina_insert","2 || ${article.abstract}")
            repository.insertArticle(result = article)
            emit(Resource.Success<Any>("Success"))
        } catch (e: Exception) {
            emit(Resource.Error<Any>(e.localizedMessage ?: "an Error Occurred"))
        }
    }
}