package es.ajrpachon.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserRootListDto(
    @Json(name = "results" ) var results : List<UserDto> = listOf(),
)
