package es.ajrpachon.data.datasource

import es.ajrpachon.domain.common.models.user.UserBo

interface UserListRemoteDataSource {

    suspend fun getUserList(page: Int): List<UserBo>
}