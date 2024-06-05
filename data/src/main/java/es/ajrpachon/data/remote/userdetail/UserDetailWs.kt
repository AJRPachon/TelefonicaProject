package es.ajrpachon.data.remote.userdetail

import es.ajrpachon.data.remote.dto.UserDto
import retrofit2.http.GET

interface UserDetailWs {

    @GET("?page=1&results=10&seed=abc") //TODO CAMBIAR GET
    suspend fun getUserDetail(id : String): UserDto
}