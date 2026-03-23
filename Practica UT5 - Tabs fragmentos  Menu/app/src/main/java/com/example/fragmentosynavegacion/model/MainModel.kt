package com.example.fragmentosynavegacion.model

import kotlinx.coroutines.handleCoroutineException

class MainModel {

    fun generarNum(): Int {
        return (1900..2200).random()
    }

    fun esBisiesto(num: Int): Boolean {
        return (num % 4 == 0 && (num % 100 != 0 || num % 400 == 0))
    }

    fun validarBisiesto(num: Int, opcion: String): Int {
        val bisiesto = esBisiesto(num)
        return if ((opcion == "SI" && bisiesto) || (opcion == "NO" && !bisiesto)) 0 else -1
    }

    fun validarDivisible(num: Int, opciones: MutableList<String>): Int {
        val correctas = mutableListOf<String>()

        if (num % 2 == 0) correctas.add("2")
        if (num % 3 == 0) correctas.add("3")
        if (num % 5 == 0) correctas.add("5")
        if (num % 10 == 0) correctas.add("10")


        if (correctas.isEmpty()) {
            correctas.add("ninguno")
        }
        return if (opciones.sorted() == correctas.sorted()) 0 else -1
    }
}