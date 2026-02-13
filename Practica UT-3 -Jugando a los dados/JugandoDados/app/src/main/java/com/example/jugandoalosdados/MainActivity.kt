package com.example.jugandoalosdados

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), DialogInterface.OnClickListener {

    lateinit var tvSaldo: TextView
    lateinit var tvSaldoTexto: TextView
    lateinit var btnParImpar: Button
    lateinit var btnMayorMenorQue7: Button
    lateinit var spinnerOpciones: Spinner

    lateinit var tilApuesta: TextInputLayout
    lateinit var etIntroducirNumero: TextInputEditText

    lateinit var btnLanzarDados: Button
    lateinit var ivResultado: ImageView
    lateinit var tvDadosResultado: TextView

    lateinit var avisos: View

    private var opcionJuego = ""
    private var saldo = 100

    private lateinit var miCorrutina: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvSaldo = findViewById(R.id.tvSaldo)
        tvSaldoTexto = findViewById(R.id.tvSaldoTexto)
        btnParImpar = findViewById(R.id.btnParImpar)
        btnMayorMenorQue7 = findViewById(R.id.btnMayorMenorQue7)
        spinnerOpciones = findViewById(R.id.spinnerOpciones)

        tilApuesta = findViewById(R.id.tilApuesta)
        etIntroducirNumero = findViewById(R.id.etIntroducirNumero)

        btnLanzarDados = findViewById(R.id.btnLanzarDados)
        ivResultado = findViewById(R.id.ivResultado)
        tvDadosResultado = findViewById(R.id.tvDadosResultado)

        avisos = findViewById(R.id.avisos)

        actulizarSaldo()

        btnParImpar.setOnClickListener {
            opcionJuego = "PARIMPAR"
            cargarSpinnerOpciones(listOf("PAR", "IMPAR"))
        }

        btnMayorMenorQue7.setOnClickListener {
            opcionJuego = "MAYORMENOR"
            cargarSpinnerOpciones(listOf("MAYOR QUE 7", "MENOR QUE 7"))
        }

        btnLanzarDados.setOnClickListener {

            if (opcionJuego == "") {
                Snackbar.make(avisos, "Debes elegir tipo de juego", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (spinnerOpciones.adapter == null || spinnerOpciones.selectedItem == null) {
                Snackbar.make(avisos, "Selecciona una opción de apuesta.", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val opcion = spinnerOpciones.selectedItem.toString()

            val apuestaTexto = etIntroducirNumero.text?.toString()?.trim().orEmpty()
            val apuesta = apuestaTexto.toIntOrNull()

            if (apuesta == null) {
                tilApuesta.error = "Introduce un número entero válido"
                return@setOnClickListener
            }
            if (apuesta <= 0) {
                tilApuesta.error = "La apuesta debe ser mayor que 0"
                return@setOnClickListener
            }
            if (apuesta > saldo) {
                tilApuesta.error = "No puedes apostar más que tu saldo"
                return@setOnClickListener
            }


            tvDadosResultado.text = ""
            Glide.with(this)
                .asGif()
                .load(R.drawable.dado_imagen_animada_0092)
                .into(ivResultado)

            // Bloqueamos el botón mientras rueda
            btnLanzarDados.isEnabled = false

            // 2) Corrutina con delay de 3 segundos
            miCorrutina = lifecycleScope.launch {

                delay(3000)

                val dado1 = (1..6).random()
                val dado2 = (1..6).random()
                val suma = dado1 + dado2

                var gana = false

                if (opcionJuego == "PARIMPAR") {
                    if (opcion == "PAR" && suma % 2 == 0) gana = true
                    if (opcion == "IMPAR" && suma % 2 != 0) gana = true
                } else if (opcionJuego == "MAYORMENOR") {
                    // (dejo tu lógica tal cual)
                    if (opcion == "MAYOR QUE 7" && suma >= 7) gana = true
                    if (opcion == "MENOR QUE 7" && suma <= 7) gana = true
                }

                if (gana) {
                    saldo += apuesta
                    ivResultado.setImageResource(R.drawable.ganar_dados)
                    Snackbar.make(avisos, "Has ganado", Snackbar.LENGTH_SHORT).show()
                } else {
                    saldo -= apuesta
                    ivResultado.setImageResource(R.drawable.perder_dados)
                    Snackbar.make(avisos, "Has perdido", Snackbar.LENGTH_SHORT).show()
                }

                tvDadosResultado.text = "Han salido: $dado1 y $dado2 (suma $suma)"
                actulizarSaldo()

                if (saldo == 0) {
                    Snackbar.make(avisos, "No tienes saldo pichón", Snackbar.LENGTH_SHORT).show()
                }

                btnLanzarDados.isEnabled = true

                mostrarDialogoSeguir()
            }
        }
    }

    private fun mostrarDialogoSeguir() {
        var myAlert = AlertDialog.Builder(this)

        myAlert.setTitle("Jugando a los dados")
        myAlert.setMessage("¿Desea seguir jugando?")

        myAlert.setNegativeButton("Salir del juego", this)
        myAlert.setPositiveButton("Seguir jugando", this)

        myAlert.create().show()
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        when (which) {
            DialogInterface.BUTTON_POSITIVE -> dialog?.dismiss()
            DialogInterface.BUTTON_NEGATIVE -> finish()
        }
    }

    private fun cargarSpinnerOpciones(lista: List<String>) {
        val miAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
        miAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerOpciones.adapter = miAdapter
    }

    private fun actulizarSaldo() {
        tvSaldo.text = "$saldo"
    }
}
