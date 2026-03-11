package com.example.recyclerviewapidog

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewapidog.databinding.ActivityMainBinding
import com.example.recyclerviewapidog.model.Datos
import com.example.recyclerviewapidog.model.DogRespuesta
import com.example.recyclerviewapidog.recycler.MiAdaptador
import com.example.recyclerviewapidog.viewmodel.RecyclerViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var misDatos: Datos
    private val miViewModel: RecyclerViewModel by viewModels()
    private lateinit var miAdaptador: MiAdaptador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val miLayout = LinearLayoutManager(this)
        binding.rvPerros.layoutManager = miLayout
        binding.btnBuscar.setOnClickListener {
            val raza = binding.etBuscar.text.toString().trim().lowercase()

            if (raza.isEmpty()) {
                Toast.makeText(this,"Introduce una raza",Toast.LENGTH_SHORT).show()
            } else {
                miViewModel.recuperarFotosPaginacion(raza)
            }
        }

        miViewModel.datos.observe(this) { respuesta ->

            if (respuesta.status == "success" && !respuesta.message.isNullOrEmpty()) {
                binding.rvPerros.adapter = MiAdaptador(respuesta)
            } else {Toast.makeText(this,"No se encontraron fotos de perros de esa raza",Toast.LENGTH_SHORT).show()
            }
        }

        miViewModel.datosScroll.observe(this@MainActivity) {

            when (it.status) {
                "success" -> {
                    if (it.paginaActual == 1) {
                        misDatos = it
                        miAdaptador = MiAdaptador(DogRespuesta(it.status, it.message))
                        binding.rvPerros.adapter = miAdaptador
                    } else {
                        miAdaptador.notifyItemRangeInserted(
                            it.paginaActual!! * 10,
                            it.message!!.size
                        )
                    }
                }
                "error" -> {Toast.makeText(this@MainActivity,"No hay fotos de esa raza",Toast.LENGTH_SHORT).show() }
            }
        }

        binding.rvPerros.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                var finalScroll = false
                if (miLayout.findLastVisibleItemPosition() % 10 >= 9 && miLayout.findLastVisibleItemPosition() / 10 ==
                    (misDatos.paginaActual!! - 1)
                ) {
                    finalScroll = true
                }

                if (finalScroll && misDatos.paginaActual!! < misDatos.numPaginas!!) {
                    Snackbar
                        .make(
                            binding.root,
                            "Si quieres recuperar mas fotos pulsa:",
                            Snackbar.LENGTH_SHORT
                        )
                        .setAction("Cargar mas fotos") {
                            miViewModel.scrollFotos()
                        }
                        .show()
                }
            }
        })
    }
}