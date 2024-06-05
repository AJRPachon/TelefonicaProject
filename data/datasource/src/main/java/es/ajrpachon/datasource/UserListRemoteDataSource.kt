package es.ajrpachon.datasource

import es.ajrpachon.model.UserBo

interface UserListRemoteDataSource {

    suspend fun getUserList(): List<UserBo>
}