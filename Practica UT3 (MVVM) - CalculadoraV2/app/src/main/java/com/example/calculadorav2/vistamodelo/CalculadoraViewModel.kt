package com.example.calculadorav2.vistamodelo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculadorav2.modelo.CalculadoraModelo
import com.example.calculadorav2.modelo.EstadoCalculadora
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CalculadoraViewModel : ViewModel() {

    private val modelo = CalculadoraModelo()

    private val _estadoObservable = MutableStateFlow<EstadoCalculadora>(modelo.estadoActual)
    val estadoObservable: StateFlow<EstadoCalculadora> = _estadoObservable
    private var miCorrutina: Job? = null

    private fun lanzarMiCorrutina(accion: suspend () -> EstadoCalculadora) {

        miCorrutina = viewModelScope.launch {
            _estadoObservable.value = accion()
        }
    }

    fun numero(n: Int) {
        lanzarMiCorrutina { modelo.pulsarNumero(n) }
    }

    fun operacion(op: String) {
        lanzarMiCorrutina { modelo.pulsarOperacion(op) }
    }

    fun igual() {
        lanzarMiCorrutina { modelo.calcular() }
    }

    fun clear() {
        lanzarMiCorrutina { modelo.limpiar() }
    }
}
