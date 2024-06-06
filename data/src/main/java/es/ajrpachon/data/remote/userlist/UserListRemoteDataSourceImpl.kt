package es.ajrpachon.data.remote.userlist

import es.ajrpachon.data.datasource.UserListRemoteDataSource
import es.ajrpachon.data.remote.dto.util.toBo
import es.ajrpachon.domain.common.models.user.UserBo
import javax.inject.Inject

class UserListRemoteDataSourceImpl @Inject constructor(
    private val userListWs: UserListWs
) : UserListRemoteDataSource {
    override suspend fun getUserList(page: Int): List<UserBo> {
        return userListWs.getUserList(1, "10", "abc").results.map { it.toBo() } //No funciona bien el paginado
    }

}