package es.ajrpachon.datatest.remote.userlist

import es.ajrpachon.datatest.remote.userlist.dto.UserRootListDto
import retrofit2.http.GET

interface UserListWs {

    @GET("?results=10")
    suspend fun getUserList(): UserRootListDto
}