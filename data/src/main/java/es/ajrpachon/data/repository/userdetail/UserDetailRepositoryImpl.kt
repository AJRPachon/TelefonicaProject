package es.ajrpachon.data.repository.userdetail

import es.ajrpachon.data.datasource.UserDetailRemoteDataSource
import es.ajrpachon.domain.common.models.user.UserBo
import es.ajrpachon.domain.common.repositories.UserDetailRepository
import es.ajrpachon.domain.common.usecase.dispatchers.AppDispatchers
import es.ajrpachon.domain.common.util.RemoteResponse
import es.ajrpachon.domain.common.util.RepositoryResponse

internal class UserDetailRepositoryImpl(
    private val remote: UserDetailRemoteDataSource,
    private val appDispatchers: AppDispatchers
): UserDetailRepository {
    override suspend fun getUserDetail(id: String): RepositoryResponse<UserBo> {
        return object: RemoteResponse<UserBo>(appDispatchers) {
            override suspend fun requestRemoteCall(): UserBo {
                return remote.getUserDetail(id)
            }
        }.build()
    }


}