package es.ajrpachon.data.remote.dto

import com.squareup.moshi.Json

data class UserNameDto(
    @Json(name = "title") var title: String? = "",
    @Json(name = "first") var firstName: String? = "",
    @Json(name = "last") var lastName: String? = ""
)
