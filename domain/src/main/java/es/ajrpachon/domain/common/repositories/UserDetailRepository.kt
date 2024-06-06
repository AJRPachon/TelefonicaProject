package es.ajrpachon.domain.common.repositories

import es.ajrpachon.domain.common.models.user.UserBo
import es.ajrpachon.domain.common.util.RepositoryResponse

interface UserDetailRepository {

    suspend fun getUserDetail(uuid : String) : RepositoryResponse<UserBo>

}
