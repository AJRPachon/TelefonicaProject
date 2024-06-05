package es.ajrpachon.repository.userlist

import es.ajrpachon.data.repository.util.AppDispatchers
import es.ajrpachon.data.repository.util.RemoteResponse
import es.ajrpachon.data.repository.util.RepositoryResponse
import es.ajrpachon.datatest.datasource.UserListRemoteDataSource
import es.ajrpachon.datatest.model.UserBo

interface UserListRepository {

    suspend fun getUserList() : RepositoryResponse<List<UserBo>>

}

internal class UserListRepositoryImpl(
    private val remote: UserListRemoteDataSource,
    private val appDispatchers: AppDispatchers
): UserListRepository {

    override suspend fun getUserList(): RepositoryResponse<List<UserBo>> {
        return object : RemoteResponse<List<UserBo>>(appDispatchers) {
            override suspend fun requestRemoteCall(): List<UserBo> {
                return remote.getUserList()
            }
        }.build()
    }

}