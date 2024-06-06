package es.ajrpachon.domain.common.models.user

data class UserLoginBo(
    val uuid: String? = "",
    val username: String? = "",
    val password: String? = "",
)
