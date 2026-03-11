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
    lateinit var fotosPerrosCargado : DogRespuesta

    lateinit var misDatos : Datos

    suspend fun recuperarFotosPaginacion(raza: String) : Datos = withContext(Dispatchers.IO) {
        val respuesta = retrofitApi.retrofitService.getFotosPerros(raza)

        if (respuesta.isSuccessful){
            fotosPerrosCargado = DogRespuesta(respuesta.body()!!.status, respuesta.body()!!.message)
            if (fotosPerrosCargado.message!!.size > 0){
                var numPaginas : Int = fotosPerrosCargado.message!!.size / 10
                if (fotosPerrosCargado.message!!.size % 10 != 0) numPaginas++
                misDatos = Datos(fotosPerrosCargado.status, numPaginas, 1, mutableListOf())
                var rango = Math.min(fotosPerrosCargado.message!!.size - 1,9)
                for (i in 0..rango){
                    misDatos.message!!.add(fotosPerrosCargado.message!!.get(i))
                }
            }
            misDatos!!
        } else {
            misDatos = Datos("error", null,null,null)
            misDatos!!
        }
    }

    fun scrollFotos() : Datos{
        var inicio : Int
        var fin : Int
        inicio = misDatos.paginaActual!! * 10
        misDatos.paginaActual = misDatos.paginaActual!! + 1
        if (misDatos.paginaActual!! < misDatos.numPaginas!!) {
            fin = (misDatos.paginaActual!! * 10 - 1)
        } else {
            fin = (fotosPerrosCargado.message!!.size - 1)
        }
        for (i in inicio..fin){
            misDatos.message!!.add(fotosPerrosCargado.message!!.get(i))
        }
        return misDatos
    }
}