package es.ajrpachon.domain.common.util

import es.ajrpachon.domain.common.util.error.AsyncError
import es.ajrpachon.domain.common.util.error.KoreException
import org.slf4j.Logger

fun <ResultType> manageException(throwable: Throwable, log: Logger? = null): AsyncResult<ResultType> {
    log?.warn("An error happened: ", throwable)
    val asyncError =
        (throwable as? KoreException)?.asyncError ?: AsyncError.UnknownError("An error happened", throwable)
    return AsyncResult.Error(asyncError, null)
}