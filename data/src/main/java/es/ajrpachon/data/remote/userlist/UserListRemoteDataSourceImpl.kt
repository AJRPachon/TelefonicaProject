package es.ajrpachon.data.remote.userlist

import es.ajrpachon.data.datasource.UserListRemoteDataSource
import es.ajrpachon.data.remote.dto.util.toBo
import es.ajrpachon.domain.common.models.user.UserBo

class UserListRemoteDataSourceImpl(
    private val userListWs: UserListWs
) : UserListRemoteDataSource {
    override suspend fun getUserList(page: Int): List<UserBo> {
        return userListWs.getUserList().results.map { it.toBo() }
    }

}