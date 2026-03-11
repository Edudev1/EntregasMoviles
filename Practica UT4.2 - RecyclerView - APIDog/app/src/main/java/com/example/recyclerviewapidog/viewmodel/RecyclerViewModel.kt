package com.example.recyclerviewapidog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerviewapidog.model.Datos
import com.example.recyclerviewapidog.model.DogRespuesta
import com.example.recyclerviewapidog.model.MainState
import kotlinx.coroutines.launch
import java.util.ArrayList

class RecyclerViewModel : ViewModel() {

    val miEstado = MainState()

    private val _datos = MutableLiveData<DogRespuesta>()

    val datos: LiveData<DogRespuesta> get() = _datos

    fun devuelveFotos(raza: String){
        viewModelScope.launch {
            _datos.value = miEstado.recuperarFotos(raza)
        }
    }

    private val _datosScroll = MutableLiveData<Datos>(Datos(null.toString(), null, null, ArrayList()))

    val datosScroll : LiveData<Datos> get() = _datosScroll

    fun recuperarFotosPaginacion(raza: String){
        viewModelScope.launch {
            _datosScroll.value = miEstado.recuperarFotosPaginacion(raza)
        }
    }

    fun scrollFotos() {
        viewModelScope.launch {
            _datosScroll.value = miEstado.scrollFotos()
        }
    }
}