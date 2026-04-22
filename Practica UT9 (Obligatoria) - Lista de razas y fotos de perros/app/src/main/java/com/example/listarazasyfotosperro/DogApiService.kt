package com.example.listarazasyfotosperro

import retrofit2.http.GET
import retrofit2.http.Path

interface DogAPIService {

    @GET("breeds/list/all")
    suspend fun getRazas(): RazasResponse

    @GET("breed/{raza}/images")
    suspend fun getImagenesRaza(
        @Path("raza") raza: String
    ): DogRespuesta

    @GET("breed/{raza}/{subraza}/images")
    suspend fun getImagenesSubraza(
        @Path("raza") raza: String,
        @Path("subraza") subraza: String
    ): DogRespuesta
}