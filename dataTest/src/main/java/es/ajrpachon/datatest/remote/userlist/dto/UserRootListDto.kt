package es.ajrpachon.datatest.remote.userlist.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import es.ajrpachon.datatest.remote.userlist.dto.UserDto

@JsonClass(generateAdapter = true)
data class UserRootListDto(
    @Json(name = "results" ) var results : ArrayList<UserDto> = arrayListOf(),
)
