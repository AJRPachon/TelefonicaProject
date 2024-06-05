package es.ajrpachon.datatest.remote.error

import com.squareup.moshi.JsonDataException
import es.ajrpachon.datatest.model.error.AsyncError
import es.ajrpachon.datatest.model.error.KoreException
import org.slf4j.LoggerFactory
import retrofit2.HttpException
import java.net.UnknownHostException
import kotlin.coroutines.cancellation.CancellationException

object RemoteErrorManagement {

    private val log = LoggerFactory.getLogger(RemoteErrorManagement::class.java.simpleName)

    /**
     * Calls the specified function [block] and returns its result. Catch any Exception and convert to a SdosException.
     */
    inline fun <T> wrap(block: () -> T): T {
        return try {
            block()
        } catch (cancellation: CancellationException) {
            // Do not intercept and wrap CancellationException, this is needed to manage the different coroutines
            // to know if those have been cancelled
            throw cancellation
        } catch (e: Throwable) {
            throw KoreException(processError(e))
        }
    }

    fun processError(throwable: Throwable): AsyncError {
        log.info("RemoteErrorManagement", throwable)
        return when (throwable) {
            is HttpException -> processRetrofitError(throwable)
            is UnknownHostException -> AsyncError.ConnectionError(throwable.message ?: "Error de conexion")
            is JsonDataException -> AsyncError.DataParseError(throwable.message ?: "Error de parseo")
            else -> AsyncError.UnknownError(throwable.message ?: "Error desconocido", throwable)
        }
    }

    private fun processRetrofitError(httpException: HttpException): AsyncError {
        val errorCode = httpException.code()
        val url = httpException.response()?.raw()?.request?.url?.toString() ?: ""
        return AsyncError.ServerError(errorCode, url)
    }
}