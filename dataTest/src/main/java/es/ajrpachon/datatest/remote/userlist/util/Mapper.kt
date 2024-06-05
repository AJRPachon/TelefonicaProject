package es.ajrpachon.datatest.remote.userlist.util

import es.ajrpachon.datatest.model.UserBo
import es.ajrpachon.datatest.model.UserLocationBo
import es.ajrpachon.datatest.model.UserNameBo
import es.ajrpachon.datatest.model.UserPictureBo
import es.ajrpachon.datatest.remote.userlist.dto.UserDto
import es.ajrpachon.datatest.remote.userlist.dto.UserIdDto
import es.ajrpachon.datatest.remote.userlist.dto.UserLocationDto
import es.ajrpachon.datatest.remote.userlist.dto.UserNameDto
import es.ajrpachon.datatest.remote.userlist.dto.UserPictureDto


internal fun UserDto.toBo() = UserBo(
    gender = userGender,
    userName = userNameDto?.toBo(),
    location = userLocationDto?.toBo(),
    picture = userPictureDto?.toBo(),
    id = userId?.toBo()
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

internal fun UserIdDto.toBo() = id