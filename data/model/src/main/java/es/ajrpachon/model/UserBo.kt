package es.ajrpachon.model

data class UserBo (
    var gender: String? = "",
    var userName: UserNameBo? = UserNameBo(),
    var location: UserLocationBo? = UserLocationBo(),
    var picture: UserPictureBo? = UserPictureBo(),
    var id : String? = ""
)
