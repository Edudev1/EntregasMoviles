package com.example.jugandoalosdados

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.selects.select

class MainActivity : AppCompatActivity() {

    lateinit var tvSaldo : TextView
    lateinit var tvSaldoTexto : TextView
    lateinit var btnParImpar : Button
    lateinit var btnMayorMenorQue7 : Button
    lateinit var spinnerOpciones : Spinner
    lateinit var etIntoducirNumero : EditText
    lateinit var btnLanzarDados : Button

    lateinit var avisos : View

    private var opcionJuego = ""
    private var saldo = 100


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvSaldo = this.findViewById(R.id.tvSaldo)
        tvSaldoTexto = this.findViewById(R.id.tvSaldoTexto)
        btnParImpar = this.findViewById(R.id.btnParImpar)
        btnMayorMenorQue7 = this.findViewById(R.id.btnMayorMenorQue7)
        spinnerOpciones = this.findViewById(R.id.spinnerOpciones)
        etIntoducirNumero = this.findViewById(R.id.etIntroducirNumero)
        btnLanzarDados = this.findViewById(R.id.btnLanzarDados)
        avisos = this.findViewById(R.id.avisos)

        actulizarSaldo()


        btnParImpar.setOnClickListener {
            opcionJuego = "PARIMPAR"
            val opciones = listOf("PAR", "IMPAR")
            cargarSpinnerOpciones(opciones)
        }

        btnMayorMenorQue7.setOnClickListener {
            opcionJuego = "MAYORMENOR"
            val opciones = listOf("MAYOR QUE 7", "MENOR QUE 7")
            cargarSpinnerOpciones(opciones)
        }

        btnLanzarDados.setOnClickListener {
            // Validamos que ha elegido un tipo de juego
            if (opcionJuego ==""){
                Snackbar.make(avisos, "Debes elegir tipo de juego", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validamos la apuesta
            val apuestaTexto = etIntoducirNumero.text.toString().trim()
            val apuesta = apuestaTexto

            if (apuesta == null){
                Snackbar.make(avisos, "Introduce un numero entero valido", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (apuesta <= 0){
                Snackbar.make(avisos, "La apuesta debe ser mayor que 0", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (apuesta > saldo) {
                Snackbar.make(avisos, "No puedes a`postar mas que tu saldo", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
    }
        private fun cargarSpinnerOpciones(lista: List<String>) {
            val miAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
            miAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerOpciones.adapter = miAdapter
        }

        private fun actulizarSaldo(){
            tvSaldo.text = "Saldo: ${saldo}"
        }
    }