package es.ajrpachon.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class UserIdDto(
    @Json(name = "value") var id: String? = "",
)