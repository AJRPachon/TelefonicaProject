package es.ajrpachon.data.remote.userlist

import es.ajrpachon.data.remote.dto.UserRootListDto
import retrofit2.http.GET

interface UserListWs {

    @GET("?page=1&results=10&seed=abc")
    suspend fun getUserList(): UserRootListDto
}