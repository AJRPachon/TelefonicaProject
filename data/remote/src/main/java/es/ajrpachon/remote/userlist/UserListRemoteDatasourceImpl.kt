package es.ajrpachon.remote.userlist

import es.ajrpachon.datasource.UserListRemoteDataSource
import es.ajrpachon.model.UserBo
import es.ajrpachon.remote.userlist.util.toBo

class UserListRemoteDatasourceImpl(
    private val userListWs: UserListWs
) : UserListRemoteDataSource {
    override suspend fun getUserList(): List<UserBo> {
        return userListWs.getUserList().results.map { it.toBo() }
    }

}