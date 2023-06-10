package com.minamagid.thechallenge.domain.use_case.delete_table

import com.minamagid.thechallenge.common.Resource
import com.minamagid.thechallenge.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteTableUseCase @Inject constructor(val repository: Repository) {
    operator fun invoke(
    ): Flow<Resource<*>> = flow {
        try {
            repository.deleteLocalTable()
            emit(Resource.Success<Any>("Success"))
        } catch (e: Exception) {
            emit(Resource.Error<Any>(e.localizedMessage ?: "an Error Occurred"))
        }
    }
}