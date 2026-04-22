package com.example.listarazasyfotosperro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListaPerrosViewModel : ViewModel() {

    private val _listaRazas = MutableStateFlow<List<ListaPerrosState>>(emptyList())
    val listaRazas: StateFlow<List<ListaPerrosState>> = _listaRazas

    private val _listaImagenes = MutableStateFlow<List<String>>(emptyList())
    val listaImagenes: StateFlow<List<String>> = _listaImagenes

    fun cargarRazas() {
        viewModelScope.launch {
            val respuesta = RetrofitApi.retrofitService.getRazas()
            val listaTemporal = mutableListOf<ListaPerrosState>()

            for ((raza, subrazas) in respuesta.message) {
                if (subrazas.isEmpty()) {
                    listaTemporal.add(ListaPerrosState(raza, ""))
                } else {
                    for (subraza in subrazas) {
                        listaTemporal.add(ListaPerrosState(raza, subraza))
                    }
                }
            }

            _listaRazas.value = listaTemporal.sortedBy {
                if (it.subraza.isEmpty()) it.raza else "${it.raza} - ${it.subraza}"
            }
        }
    }

    fun cargarImagenes(raza: String, subraza: String) {
        viewModelScope.launch {
            val respuesta = if (subraza.isEmpty()) {
                RetrofitApi.retrofitService.getImagenesRaza(raza)
            } else {
                RetrofitApi.retrofitService.getImagenesSubraza(raza, subraza)
            }

            _listaImagenes.value = respuesta.message
        }
    }
}