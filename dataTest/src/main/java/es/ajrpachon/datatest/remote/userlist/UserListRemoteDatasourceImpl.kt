package es.ajrpachon.datatest.remote.userlist

import es.ajrpachon.datatest.datasource.UserListRemoteDataSource
import es.ajrpachon.datatest.model.UserBo
import es.ajrpachon.datatest.remote.userlist.util.toBo

class UserListRemoteDatasourceImpl(
    private val userListWs: UserListWs
) : UserListRemoteDataSource {
    override suspend fun getUserList(): List<UserBo> {
        return userListWs.getUserList().results.map { it.toBo() }
    }

}