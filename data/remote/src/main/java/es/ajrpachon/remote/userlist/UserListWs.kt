package es.ajrpachon.remote.userlist

import es.ajrpachon.remote.userlist.dto.UserRootListDto
import retrofit2.http.GET

interface UserListWs {

    @GET("?results=10")
    suspend fun getUserList(): UserRootListDto
}