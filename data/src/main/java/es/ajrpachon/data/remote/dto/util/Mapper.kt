package es.ajrpachon.data.remote.dto.util

import es.ajrpachon.data.remote.dto.UserDto
import es.ajrpachon.data.remote.dto.UserLocationDto
import es.ajrpachon.data.remote.dto.UserLoginDto
import es.ajrpachon.data.remote.dto.UserNameDto
import es.ajrpachon.data.remote.dto.UserPictureDto
import es.ajrpachon.domain.common.models.user.UserBo
import es.ajrpachon.domain.common.models.user.UserLocationBo
import es.ajrpachon.domain.common.models.user.UserLoginBo
import es.ajrpachon.domain.common.models.user.UserNameBo
import es.ajrpachon.domain.common.models.user.UserPictureBo


internal fun UserDto.toBo() = UserBo(
    gender = userGender,
    userName = userNameDto?.toBo(),
    location = userLocationDto?.toBo(),
    picture = userPictureDto?.toBo(),
    login = userLogin?.toBo(),
    email = userEmail
)

internal fun UserNameDto.toBo() = UserNameBo(
    title = title, firstName = firstName, lastName = lastName
)

internal fun UserLocationDto.toBo() = UserLocationBo(
    city = city, country = country
)

internal fun UserPictureDto.toBo() = UserPictureBo(
    thumbnail = thumbnail
)

internal fun UserLoginDto.toBo() =
    UserLoginBo(
        uuid = uuid,
        username = username,
        password = password
    )