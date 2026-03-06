package com.example.recyclerviewapidog

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewapidog.databinding.ActivityMainBinding
import com.example.recyclerviewapidog.model.MainState
import com.example.recyclerviewapidog.recycler.MiAdaptador
import com.example.recyclerviewapidog.viewmodel.RecyclerViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val miViewModel : RecyclerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.rvPerros.layoutManager = LinearLayoutManager(this)
        binding.btnBuscar.setOnClickListener {
            val raza = binding.etBuscar.text.toString().trim().lowercase()

            if (raza.isEmpty()) {
                Toast.makeText(this, "Introduce una raza", Toast.LENGTH_SHORT).show()
            } else {
                miViewModel.devuelveFotos(raza)
            }
        }

        miViewModel.datos.observe(this) {
            respuesta -> if (respuesta.status == "success" && !respuesta.message.isNullOrEmpty()) {
                        } else {
                            Toast.makeText(this, "No se encontraron fotos de perros de esa raza",
                                Toast.LENGTH_SHORT).show()
        }
        }
    }
}