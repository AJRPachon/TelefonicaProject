package es.ajrpachon.repository.util

import es.ajrpachon.model.error.AsyncError


sealed class AsyncResult<out T>(open val data: T?) {
    data class Success<out T>(override val data: T?) : AsyncResult<T>(data)
    data class Error<out T>(val error: AsyncError, override val data: T?) : AsyncResult<T>(data)
    data class Loading<out T>(override val data: T?) : AsyncResult<T>(data)
}
