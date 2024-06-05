package es.ajrpachon.data.repository.userlist

import es.ajrpachon.data.datasource.UserListRemoteDataSource
import es.ajrpachon.domain.common.models.user.UserBo
import es.ajrpachon.domain.common.repositories.UserListRepository
import es.ajrpachon.domain.common.usecase.dispatchers.AppDispatchers
import es.ajrpachon.domain.common.util.RemoteResponse
import es.ajrpachon.domain.common.util.RepositoryResponse


internal class UserListRepositoryImpl(
    private val remote: UserListRemoteDataSource,
    private val appDispatchers: AppDispatchers
): UserListRepository {

    override suspend fun getUserList(page : Int): RepositoryResponse<List<UserBo>> {
        return object : RemoteResponse<List<UserBo>>(appDispatchers) {
            override suspend fun requestRemoteCall(): List<UserBo> {
                return remote.getUserList(page)
            }
        }.build()
    }

}