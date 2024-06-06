package es.ajrpachon.data.remote.userlist

import es.ajrpachon.data.remote.dto.UserRootListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserListWs {

    @GET("?")
    suspend fun getUserList(@Query(value = "page") page: Int, @Query(value = "results") results: String, @Query(value = "seed") seed: String): UserRootListDto
}