package es.ajrpachon.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class UserLocationDto(
    @Json(name = "city") val city: String? = "",
    @Json(name = "country") val country: String? = "",
)
