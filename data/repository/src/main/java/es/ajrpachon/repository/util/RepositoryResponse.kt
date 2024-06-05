package es.ajrpachon.data.repository.util

import es.ajrpachon.repository.util.AsyncResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

interface RepositoryResponse<out ResultType> {

    suspend fun flow(): Flow<AsyncResult<ResultType>>

    suspend fun value(): AsyncResult<ResultType>

}

fun <ResultType> emptyRepositoryResponse(): RepositoryResponse<ResultType> = EmptyRepositoryResponse

private object EmptyRepositoryResponse : RepositoryResponse<Nothing> {
    override suspend fun flow(): Flow<AsyncResult<Nothing>> {
        return emptyFlow()
    }

    override suspend fun value(): AsyncResult<Nothing> = AsyncResult.Success(null)
}