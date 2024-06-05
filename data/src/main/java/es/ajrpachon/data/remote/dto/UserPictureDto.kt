package es.ajrpachon.data.remote.dto

import com.squareup.moshi.Json

data class UserPictureDto(
    @Json(name = "thumbnail") val thumbnail: String? = "",
)
