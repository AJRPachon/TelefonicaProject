package es.ajrpachon.datatest.remote.userlist.dto

import com.squareup.moshi.Json

data class UserIdDto(
    @Json(name = "value") var id: String? = "",
)