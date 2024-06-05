package es.ajrpachon.datatest.model.error

import es.ajrpachon.model.error.AsyncError

class KoreException(val asyncError: AsyncError) : Exception() {
    override fun toString(): String {
        return asyncError.toString()
    }
}