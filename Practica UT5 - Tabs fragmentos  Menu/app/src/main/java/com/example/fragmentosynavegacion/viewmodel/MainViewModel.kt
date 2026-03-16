package com.example.fragmentosynavegacion.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fragmentosynavegacion.model.Datos
import com.example.fragmentosynavegacion.model.MainModel

class MainViewModel : ViewModel(){
    private val model = MainModel()

    private val _datos = MutableLiveData(Datos())
    val datos: LiveData<Datos> = _datos

    fun generarNum() {
        val nuevo = Datos()
        nuevo.numGenerado = model.generarNum()
        nuevo.estado = 1
        _datos.value = nuevo
    }
    fun resetEstado() {
        val actual = _datos.value ?: return
        actual.estado = 1
        _datos.value = actual
    }

    fun validarBisiesto(opcion: String){
        val actual = _datos.value ?: return
        val resultado = model.validarBisiesto(actual.numGenerado, opcion)
    }

    fun validarDivisible(opciones: MutableList<String>){
        val actual = _datos.value ?: return
        val resultado = model.validarDivisible(actual.numGenerado, opciones)

        actual.estado = resultado
        _datos.value = actual
    }

    fun hayNumeroGenerado(): Boolean {
        val actual = _datos.value ?: return false
        return actual.numGenerado != 0
    }
}