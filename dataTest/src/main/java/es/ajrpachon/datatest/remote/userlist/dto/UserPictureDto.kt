package es.ajrpachon.datatest.remote.userlist.dto

import com.squareup.moshi.Json

data class UserPictureDto(
    @Json(name = "thumbnail") val thumbnail: String? = "",
)
