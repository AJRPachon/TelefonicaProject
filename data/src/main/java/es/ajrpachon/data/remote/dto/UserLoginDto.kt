package es.ajrpachon.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class UserLoginDto(
    @Json(name = "uuid") var uuid: String? = "",
    @Json(name = "username") var username: String? = "",
    @Json(name = "password") var password: String? = "",
)