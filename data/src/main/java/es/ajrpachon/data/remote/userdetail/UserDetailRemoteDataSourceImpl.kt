package es.ajrpachon.data.remote.userdetail

import es.ajrpachon.data.datasource.UserDetailRemoteDataSource
import es.ajrpachon.data.remote.dto.util.toBo
import es.ajrpachon.domain.common.models.user.UserBo
import javax.inject.Inject

class UserDetailRemoteDataSourceImpl @Inject constructor(
    private val userDetailWs: UserDetailWs
) : UserDetailRemoteDataSource {
    override suspend fun getUserDetail(uuid: String): UserBo {
        return userDetailWs.getUserDetail(uuid).results.first().toBo()
    }


}