package es.ajrpachon.datatest.datasource

import es.ajrpachon.datatest.model.UserBo

interface UserListRemoteDataSource {

    suspend fun getUserList(): List<UserBo>
}