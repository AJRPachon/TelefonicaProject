package es.ajrpachon.data.remote.userdetail

import es.ajrpachon.data.remote.dto.UserRootListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface UserDetailWs {

    @GET("?") //TODO cuando filtras por UUID, trae a alguien con un uuid distinto, fallo de la API, probando con POSTMAN cada vez que buscas es alguien aletorio aunque cada uno tenga un uuid unico
    suspend fun getUserDetail(@Query("uuid") uuid: String): UserRootListDto
}