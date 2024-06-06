package es.ajrpachon.data.datasource

import es.ajrpachon.domain.common.models.user.UserBo

interface UserDetailRemoteDataSource {
    suspend fun getUserDetail(uuid :String): UserBo
}