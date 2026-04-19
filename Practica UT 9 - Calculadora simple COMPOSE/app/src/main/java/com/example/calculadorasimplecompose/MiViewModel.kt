package com.example.calculadorasimplecompose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MiViewModel : ViewModel() {

    private val miEstado = MiEstado()

    private val _resultado = MutableStateFlow("0.0")
    val resultado: StateFlow<String> = _resultado

    fun sumar(num1: String, num2: String) {
        _resultado.value = miEstado.sumar(num1, num2)
    }

    fun restar(num1: String, num2: String) {
        _resultado.value = miEstado.restar(num1, num2)
    }

    fun multiplicar(num1: String, num2: String) {
        _resultado.value = miEstado.multiplicar(num1, num2)
    }

    fun dividir(num1: String, num2: String) {
        _resultado.value = miEstado.dividir(num1, num2)
    }
}