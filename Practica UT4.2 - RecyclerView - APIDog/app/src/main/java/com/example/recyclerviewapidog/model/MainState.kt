package com.example.recyclerviewapidog.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class MainState {

    val retrofitApi = RetrofitApi()

    suspend fun recuperarFotos(raza: String): DogRespuesta = withContext(Dispatchers.IO) {
        val respuesta = retrofitApi.retrofitService.getFotosPerros(raza)

        if (respuesta.isSuccessful){
            DogRespuesta(respuesta.body()!!.status, respuesta.body()!!.message)
        } else {
            DogRespuesta("error", null)
        }
    }
}