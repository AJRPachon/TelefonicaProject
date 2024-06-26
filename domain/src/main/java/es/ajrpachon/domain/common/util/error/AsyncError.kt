package es.ajrpachon.domain.common.util.error

sealed class AsyncError(open val debugMessage: String) {
    data class ConnectionError(override val debugMessage: String) : AsyncError(debugMessage)
    data class DataParseError(override val debugMessage: String) : AsyncError(debugMessage)
    data class InvalidTokenError(override val debugMessage: String) : AsyncError(debugMessage)
    data class ServerError(val code: Int, override val debugMessage: String) : AsyncError(debugMessage)
    data class UnknownError(override val debugMessage: String, val errorThrowed: Throwable) : AsyncError(debugMessage)
    open class CustomError(override val debugMessage: String) : AsyncError(debugMessage)
}