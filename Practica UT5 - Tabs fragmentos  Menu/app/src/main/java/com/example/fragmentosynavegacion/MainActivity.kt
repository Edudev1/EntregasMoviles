package com.example.fragmentosynavegacion

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fragmentosynavegacion.viewmodel.MainViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var btnGenerar: Button
    private lateinit var tvNumero: TextView
    private lateinit var tabLayoutMain: TabLayout
    private lateinit var contenedor: FrameLayout
    private lateinit var toolbarMain: MaterialToolbar

    private val viewModel: MainViewModel by viewModels()

    private var pestanaActual = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnGenerar = findViewById(R.id.btnGenerar)
        tvNumero = findViewById(R.id.tvNumero)
        tabLayoutMain = findViewById(R.id.tabLayoutMain)
        contenedor = findViewById(R.id.fcvMain)
        toolbarMain = findViewById(R.id.toolbarMain)

        setSupportActionBar(toolbarMain)
        supportActionBar?.title = "Bisiesto"

        viewModel.datos.observe(this) { datos ->
            if (datos.numGenerado == 0) {
                tvNumero.text = "----"
            } else {
                tvNumero.text = datos.numGenerado.toString()
            }
        }

        btnGenerar.setOnClickListener {
            viewModel.generarNum()
            cargarFragmentoSegunPestana()
        }

        tabLayoutMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                pestanaActual = tab?.position ?: 0

                if (!viewModel.hayNumeroGenerado()) {
                    Toast.makeText(this@MainActivity, "Primero genera un número", Toast.LENGTH_SHORT).show()
                    return
                }

                viewModel.resetEstado()
                cargarFragmentoSegunPestana()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun cargarFragmentoSegunPestana() {
        val fragment = when (pestanaActual) {
            1 -> {
                supportActionBar?.title = "Divisible"
                fragmentoDivisible.newInstance("Divisible", viewModel.datos.value?.numGenerado ?: 0)
            }
            else -> {
                supportActionBar?.title = "Bisiesto"
                fragmentoBisiesto.newInstance("Bisiesto", viewModel.datos.value?.numGenerado ?: 0)
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fcvMain, fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.opSalir -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}