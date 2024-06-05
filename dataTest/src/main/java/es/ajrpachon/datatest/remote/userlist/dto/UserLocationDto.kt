package es.ajrpachon.datatest.remote.userlist.dto

import com.squareup.moshi.Json

data class UserLocationDto(
    @Json(name = "city") val city: String? = "",
    @Json(name = "country") val country: String? = "",
)
