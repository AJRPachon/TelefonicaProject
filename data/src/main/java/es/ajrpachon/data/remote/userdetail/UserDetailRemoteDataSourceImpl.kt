package es.ajrpachon.data.remote.userdetail

import es.ajrpachon.data.datasource.UserDetailRemoteDataSource
import es.ajrpachon.data.remote.dto.util.toBo
import es.ajrpachon.domain.common.models.user.UserBo

class UserDetailRemoteDataSourceImpl(
    private val userDetailWs: UserDetailWs
) : UserDetailRemoteDataSource {
    override suspend fun getUserDetail(id: String): UserBo {
        return userDetailWs.getUserDetail(id).toBo()
    }


}