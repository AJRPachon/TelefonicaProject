package es.ajrpachon.remote.userlist.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserRootListDto(
    @Json(name = "results" ) var results : ArrayList<UserDto> = arrayListOf(),
)
