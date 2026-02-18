package com.example.calculadorav2.modelo

import kotlinx.coroutines.delay

class CalculadoraModelo {
    var estadoActual = EstadoCalculadora("", "", "0", false)

    private var primerNumero: Double? = null
    private var operacion: String? = null
    private var esperandoSegundo = false
    private var segundoEmpezado = false

    private fun ponerEstado(estado: String, acumulado: String, numero: String, resultado: Boolean){
        estadoActual = EstadoCalculadora(estado,acumulado, numero, resultado)
    }

    // ---- NUMERO

    suspend fun pulsarNumero(digito: Int): EstadoCalculadora {

        delay(50)

        if (estadoActual.calcularResultado) {
            limpiar()
        }

        val actual = estadoActual.numero
        val nuevo = if (actual == "0") digito.toString()
        else actual + digito.toString()

        ponerEstado(
            "",
            estadoActual.acumulado,
            nuevo,
            false
        )

        if (esperandoSegundo) segundoEmpezado = true

        return estadoActual
    }

    // LIMPIAR
    suspend fun limpiar(): EstadoCalculadora{
        delay(50)

        primerNumero = null
        operacion = null
        esperandoSegundo = false
        segundoEmpezado = false

        ponerEstado("", "", "0", false)
            return estadoActual
    }

    // --- OPERACION
    suspend fun pulsarOperacion(op: String): EstadoCalculadora {
        delay(50)

        if (operacion != null && esperandoSegundo && !segundoEmpezado) {
            ponerEstado(
                "Debes elegir números antes de repetir la operación",
                estadoActual.acumulado,
                estadoActual.numero,
                false
            )
            return estadoActual
        }
        val numeroActual = estadoActual.numero.toDouble()

        if (primerNumero == null) {
            primerNumero = numeroActual
        }

        operacion = op
        esperandoSegundo = true
        segundoEmpezado = false

        if (primerNumero != null) {
            ponerEstado(
                "",
                "$primerNumero $op",
                "0",
                false
            )
        }
        return estadoActual
    }

    // ----- IGUAL
    suspend fun calcular(): EstadoCalculadora {
        delay(50)


        if (primerNumero == null || operacion == null){
            ponerEstado(
                "Elige una operación y dos numeros",
                estadoActual.acumulado,
                estadoActual.numero,
                false
            )
            return estadoActual
        }
        if (esperandoSegundo && !segundoEmpezado) {
            ponerEstado(
                "Debes introducir el segundo número",
                estadoActual.acumulado,
                estadoActual.numero,
                false
            )
            return estadoActual
        }

        val segundoNumero = estadoActual.numero.toDouble()
        var resultado = 0.0

        when (operacion){
            "+" -> resultado = primerNumero!! + segundoNumero
            "-" -> resultado = primerNumero!! - segundoNumero
            "*" -> resultado = primerNumero!! * segundoNumero
            "/" -> resultado = primerNumero!! / segundoNumero
        }
        ponerEstado(
            "",
            "$primerNumero $operacion $segundoNumero =",
            resultado.toString(),
            true
        )
        primerNumero = resultado
        operacion = null
        esperandoSegundo = false
        segundoEmpezado = false

        return estadoActual
    }
    }