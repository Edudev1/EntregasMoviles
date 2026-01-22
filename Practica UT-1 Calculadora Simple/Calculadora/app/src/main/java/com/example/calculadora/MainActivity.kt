package com.example.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val opt1 = findViewById<EditText>(R.id.editTextNumberDecimal)
        val opt2 = findViewById<EditText>(R.id.editTextNumberDecimal2)
        val resultadoTxt = findViewById<TextView>(R.id.Resultado)

        val btnSumar = findViewById<Button>(R.id.botonSumar)
        val btnRestar = findViewById<Button>(R.id.botonRestar)
        val btnMultiplicar = findViewById<Button>(R.id.botonMultiplicar)
        val btnDividir = findViewById<Button>(R.id.botonDividir)

        //Recoger los eventos de los botones
        btnSumar.setOnClickListener { calcular('+', opt1, opt2, resultadoTxt) }
        btnRestar.setOnClickListener { calcular('-', opt1, opt2, resultadoTxt) }
        btnMultiplicar.setOnClickListener { calcular('*', opt1, opt2, resultadoTxt) }
        btnDividir.setOnClickListener { calcular('/', opt1, opt2, resultadoTxt) }
    }
    fun calcular(
        operador: Char,
        op1: EditText,
        op2: EditText,
        resultadoTxt: TextView
    ) {
        val txt1 = op1.text.toString()
        val txt2 = op2.text.toString()

        if (txt1.isEmpty() || txt2.isEmpty()) {
            resultadoTxt.text = "Faltan los datos"
            return
        }
        val num1 = txt1.toDouble()
        val num2 = txt2.toDouble()

        val resultado = when (operador) {
            '+' -> num1 + num2
            '-' -> num1 - num2
            '*' -> num1 * num2
            '/' -> num1 / num2
            else -> 0.0
        }
        resultadoTxt.text = resultado.toString()
    }
}