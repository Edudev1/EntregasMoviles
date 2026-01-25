package com.example.quizmatematico2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private var numeroRandom: Int = 0;
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnGenerarNumero = findViewById<Button>(R.id.botonGenerar)
        val btnComprobarResultado = findViewById<Button>(R.id.botonComprobarResultado)
        val tvNumeroGenerado = findViewById<TextView>(R.id.numeroGenerado)
        val tvCompobarResultado = findViewById<TextView>(R.id.tvComprobarResultado)
        val cbDivisible2 = findViewById<CheckBox>(R.id.checkBoxDivisible2)
        val cbDivisible3 = findViewById<CheckBox>(R.id.checkBoxDivisible3)
        val cbDivisible5 = findViewById<CheckBox>(R.id.checkBoxDivisible5)
        val cbDivisible10 = findViewById<CheckBox>(R.id.checkBoxDivisible10)
        val cbNoDivisible = findViewById<CheckBox>(R.id.checkBoxDivisibleNinguno)
        val imageResultado = findViewById<ImageView>(R.id.imageResultado)


        btnGenerarNumero.setOnClickListener {
            numeroRandom = (1000..2000).random()
            tvNumeroGenerado.text = numeroRandom.toString()
        }

        btnComprobarResultado.setOnClickListener {
            val haMarcado = cbDivisible2.isChecked || cbDivisible3.isChecked || cbDivisible5.isChecked
                            cbDivisible10.isChecked || cbNoDivisible.isChecked
            if (!haMarcado) {
                tvCompobarResultado.text = "Debe escoger al menos una de las opciones"
                return@setOnClickListener
            }

            val correcto2 = numeroRandom % 2 == 0
            val correcto3 = numeroRandom % 3 == 0
            val correcto5 = numeroRandom % 5 == 0
            val correcto10 = numeroRandom % 10 == 0

            val noEsDivisiblePorNinguno = !correcto2 && !correcto3 && !correcto5 && !correcto10

            val usuarioCorrecto =
                (cbDivisible2.isChecked == correcto2) && (cbDivisible3.isChecked == correcto3) && (cbDivisible5.isChecked == correcto5)
                (cbDivisible10.isChecked == correcto10) && (cbNoDivisible.isChecked == noEsDivisiblePorNinguno)

            if (usuarioCorrecto) {
                tvCompobarResultado.text = "Correcto"
                imageResultado.setImageResource(R.drawable.ic_ok)
            } else {
                tvCompobarResultado.text = "Incorrecto"
                imageResultado.setImageResource(R.drawable.ic_ko)
            }
        }


    }
}