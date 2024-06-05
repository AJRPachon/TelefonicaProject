package es.ajrpachon.domain.common.models.user

data class UserBo (
    var gender: String? = "",
    var userName: UserNameBo? = UserNameBo(),
    var location: UserLocationBo? = UserLocationBo(),
    var picture: UserPictureBo? = UserPictureBo(),
    var id : String? = ""
)
