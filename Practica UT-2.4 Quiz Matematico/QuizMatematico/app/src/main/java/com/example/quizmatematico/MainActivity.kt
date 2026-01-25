package com.example.quizmatematico

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private var anyoActual: Int = 0
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnGenerar = findViewById<Button>(R.id.generarNumero)
        val btnComprobarResult = findViewById<Button>(R.id.comprobarResultado)
        val textViewGenerarNumero = findViewById<TextView>(R.id.numeroGenerado)
        val textViewResultado = findViewById<TextView>(R.id.resultado)
        val radioButtonSi = findViewById<RadioButton>(R.id.radioButton)
        val radioButtonNo = findViewById<RadioButton>(R.id.radioButton2)
        val switchButtonFondo = findViewById<Switch>(R.id.fondoAmarillo)
        val layoutPrincipal = findViewById<android.view.View>(R.id.main)

        switchButtonFondo.setOnCheckedChangeListener { switch, isChecked ->
            layoutPrincipal.setBackgroundColor(
                if (isChecked) Color.YELLOW
                else Color.WHITE
            )
        }

        btnGenerar.setOnClickListener {
            anyoActual = (1900..2100).random()
            textViewGenerarNumero.text = anyoActual.toString()
            textViewResultado.text = ""
        }
        btnComprobarResult.setOnClickListener {
            if (anyoActual == 0){
                textViewResultado.text = "Primero genera un número"
                return@setOnClickListener
            }
            val esBisiesto = (anyoActual % 4 == 0 && anyoActual % 100 != 0) || (anyoActual % 400 == 0)
            val usuarioDijoSi = radioButtonSi.isChecked
            val usuarioDijoNo = radioButtonNo.isChecked

            if (!usuarioDijoSi && !usuarioDijoNo) {
                textViewResultado.text = "Selecciona una opcion"
                return@setOnClickListener
            }

            if ((esBisiesto && usuarioDijoSi) || (!esBisiesto && usuarioDijoNo)){
                textViewResultado.text = "¡Correcto! Es Bisiesto"
                textViewResultado.setTextColor(Color.GREEN)
            } else {
                textViewResultado.text = "¡Fallaste! Es Bisiesto"
                textViewResultado.setTextColor(Color.RED)
            }
        }
    }
}