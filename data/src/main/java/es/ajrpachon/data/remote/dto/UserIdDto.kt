package es.ajrpachon.data.remote.dto

import com.squareup.moshi.Json

data class UserIdDto(
    @Json(name = "value") var id: String? = "",
)