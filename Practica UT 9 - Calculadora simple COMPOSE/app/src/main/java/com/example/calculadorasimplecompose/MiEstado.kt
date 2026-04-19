package com.example.calculadorasimplecompose

class MiEstado {

    private fun convertirNumero(texto: String): Double? {
        return texto.toDoubleOrNull()
    }

    fun sumar(num1: String, num2: String): String {
        val n1 = convertirNumero(num1)
        val n2 = convertirNumero(num2)

        return if (n1 != null && n2 != null) {
            (n1 + n2).toString()
        } else {
            "Error"
        }
    }

    fun restar(num1: String, num2: String): String {
        val n1 = convertirNumero(num1)
        val n2 = convertirNumero(num2)

        return if (n1 != null && n2 != null) {
            (n1 - n2).toString()
        } else {
            "Error"
        }
    }

    fun multiplicar(num1: String, num2: String): String {
        val n1 = convertirNumero(num1)
        val n2 = convertirNumero(num2)

        return if (n1 != null && n2 != null) {
            (n1 * n2).toString()
        } else {
            "Error"
        }
    }

    fun dividir(num1: String, num2: String): String {
        val n1 = convertirNumero(num1)
        val n2 = convertirNumero(num2)

        return if (n1 == null || n2 == null) {
            "Error"
        } else if (n2 == 0.0) {
            "No se puede dividir entre 0"
        } else {
            (n1 / n2).toString()
        }
    }
}