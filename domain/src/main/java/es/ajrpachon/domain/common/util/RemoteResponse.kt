package es.ajrpachon.domain.common.util

import es.ajrpachon.domain.common.usecase.dispatchers.AppDispatchers
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.slf4j.LoggerFactory

internal inline fun <T> remoteResponse(
    appDispatchers: AppDispatchers,
    crossinline block: suspend () -> T
): RepositoryResponse<T> {
    return object : RemoteResponse<T>(appDispatchers) {

        override suspend fun requestRemoteCall(): T = block()

    }.build()
}

abstract class RemoteResponse<ResultType>(private val appDispatchers: AppDispatchers) {

    private val log = LoggerFactory.getLogger(RemoteResponse::class.java.simpleName)

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
        val finalValue: AsyncResult<ResultType> = try {
                log.info("Fetch data from network")
                val networkResponse = fetchFromNetwork()
            AsyncResult.Success(networkResponse)

            } catch (exception: Exception) {
            manageException(exception, log)
        }
        emit(finalValue)
        return finalValue
    }

    private suspend fun fetchFromNetwork(): ResultType {
        val networkResponse = requestRemoteCall()
        log.info("Data fetched from network")
        return networkResponse
    }
    //endregion

    //region Abstract methods
    protected abstract suspend fun requestRemoteCall(): ResultType
    //endregion
}
