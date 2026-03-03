package com.example.recyclerviewcolores.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewcolores.model.Color
import com.example.recyclerviewcolores.model.ColorModel
import com.example.recyclerviewcolores.model.Datos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecyclerViewModel : ViewModel() {

    var miModelo = ColorModel()

    private var _datos = MutableStateFlow<Datos>(Datos("", mutableListOf<Color>()))

    val datos : StateFlow<Datos> get() = _datos

    fun retornarListaColor() {
        viewModelScope.launch {
            _datos.value = miModelo.retornarListaColores()
        }
    }
}