package es.ajrpachon.remote.userlist.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto(
    @Json(name = "gender") var userGender: String? = "",
    @Json(name = "name") var userNameDto: UserNameDto? = UserNameDto(),
    @Json(name = "location") var userLocationDto: UserLocationDto? = UserLocationDto(),
    @Json(name = "picture") var userPictureDto: UserPictureDto? = UserPictureDto(),
    @Json(name = "id") var userId: UserIdDto? = UserIdDto()
)