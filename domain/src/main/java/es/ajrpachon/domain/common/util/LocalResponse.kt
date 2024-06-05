package es.ajrpachon.domain.common.util

import es.ajrpachon.domain.common.usecase.dispatchers.AppDispatchers
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.slf4j.LoggerFactory

internal inline fun <T> localResponse(
    appDispatchers: AppDispatchers,
    crossinline block: suspend () -> T?
): RepositoryResponse<T> {
    return object : LocalResponse<T>(appDispatchers) {

        override suspend fun loadFromLocal(): T? = block()

    }.build()
}

abstract class LocalResponse<ResultType>(private val appDispatchers: AppDispatchers) {

    private val log = LoggerFactory.getLogger(LocalResponse::class.java.simpleName)

    private var flow: Flow<AsyncResult<ResultType>>? = null
    private var deferredValue: Deferred<AsyncResult<ResultType>>? = null

    //region RepositoryResponse
    private val response = object : RepositoryResponse<ResultType> {
        override suspend fun flow() = executeFlow()
        override suspend fun value() = executeBase { }
    }
    //endregion

    //region Logic Template
    fun build(): RepositoryResponse<ResultType> {
        return response
    }

    private suspend fun executeFlow(): Flow<AsyncResult<ResultType>> {
        return flow ?: flow {
            executeBase { emit(it) }
        }.flowOn(appDispatchers.io).apply { flow = this }
    }

    private suspend fun executeBase(emit: suspend (AsyncResult<ResultType>) -> Unit): AsyncResult<ResultType> {
        emit(AsyncResult.Loading(null))
        val value = try {
            val dbResult = loadFromLocal()
            log.info("Return data from local database")
            AsyncResult.Success(dbResult)

        } catch (exception: Exception) {
            manageException(exception, log)
        }
        emit(value)
        return value
    }
    //endregion

    //region Abstract methods
    protected abstract suspend fun loadFromLocal(): ResultType?
    //endregion
}
