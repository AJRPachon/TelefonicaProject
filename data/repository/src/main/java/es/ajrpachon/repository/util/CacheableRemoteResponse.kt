package es.ajrpachon.data.repository.util

import es.ajrpachon.repository.util.AsyncResult
import es.ajrpachon.repository.util.manageException
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.slf4j.LoggerFactory

abstract class CacheableRemoteResponse<ResultType>(private val appDispatchers: AppDispatchers) {

    private val log = LoggerFactory.getLogger(CacheableRemoteResponse::class.java.simpleName)

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
        val dbResult = try {
            loadFromLocal()

        } catch (e: Exception) {
            log.warn("An error happened: ", e)
            null
        }
        val finalValue: AsyncResult<ResultType> = if (dbResult == null || shouldRequestFromRemote(dbResult)) {
            try {
                log.info("Fetch data from network")
                emit(AsyncResult.Loading(dbResult)) // Dispatch latest value quickly (UX purpose)
                val networkResponse = fetchFromNetwork()
                AsyncResult.Success(networkResponse)

            } catch (exception: Exception) {
                manageException(exception, log)
            }
        } else {
            log.info("Return data from local database")
            AsyncResult.Success(dbResult)
        }
        emit(finalValue)
        return finalValue
    }

    private suspend fun fetchFromNetwork(): ResultType {
        val networkResponse = requestRemoteCall()
        log.info("Data fetched from network")
        saveRemoteResponse(networkResponse)
        return networkResponse
    }
    //endregion

    //region Abstract methods
    protected abstract suspend fun loadFromLocal(): ResultType?

    protected abstract fun shouldRequestFromRemote(localResponse: ResultType): Boolean

    protected abstract suspend fun requestRemoteCall(): ResultType

    protected abstract suspend fun saveRemoteResponse(remoteResponse: ResultType)
    //endregion
}
