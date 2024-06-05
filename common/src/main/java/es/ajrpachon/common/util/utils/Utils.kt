package es.ajrpachon.common.util.utils


fun getSecureUrl(originalUrl: String): String {
    return originalUrl.replace("http://", "https://")
}