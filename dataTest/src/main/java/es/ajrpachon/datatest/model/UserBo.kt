package es.ajrpachon.datatest.model

import es.ajrpachon.model.UserLocationBo
import es.ajrpachon.model.UserNameBo
import es.ajrpachon.model.UserPictureBo

data class UserBo (
    var gender: String? = "",
    var userName: UserNameBo? = UserNameBo(),
    var location: UserLocationBo? = UserLocationBo(),
    var picture: UserPictureBo? = UserPictureBo(),
    var id : String? = ""
)
