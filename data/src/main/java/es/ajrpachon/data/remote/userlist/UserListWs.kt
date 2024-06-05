package es.ajrpachon.data.remote.userlist

import es.ajrpachon.data.remote.dto.UserRootListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface UserListWs {

    @GET("?page={page}&results=10&seed=abc")
    suspend fun getUserList(@Path("page") page: Int): UserRootListDto
}