package com.example.calculadorav2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.activity.viewModels
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.calculadorav2.databinding.ActivityMainBinding
import com.example.calculadorav2.vistamodelo.CalculadoraViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CalculadoraViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /**viewModel.estadoObservable.observe(this) {
            binding.tvEntradaDatos.text = it.numero
            binding.tvRegistroDatos.text = it.acumulado
            if (it.estado.isNotBlank()) {Toast.makeText(this, it.estado,Toast.LENGTH_SHORT).show() }

            binding.tvEntradaDatos.text = it.numero

        }**/

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.estadoObservable.collect {
                    binding.tvEntradaDatos.text = it.numero
                    binding.tvRegistroDatos.text = it.acumulado
                    if (it.estado.isNotBlank()) {Toast.makeText(this@MainActivity, it.estado,Toast.LENGTH_SHORT).show()}
                }
            }
        }

        binding.btnNum0.setOnClickListener { viewModel.numero(0) }
        binding.btnNum1.setOnClickListener { viewModel.numero(1) }
        binding.btnNum2.setOnClickListener { viewModel.numero(2) }
        binding.btnNum3.setOnClickListener { viewModel.numero(3) }
        binding.btnNum4.setOnClickListener { viewModel.numero(4) }
        binding.btnNum5.setOnClickListener { viewModel.numero(5) }
        binding.btnNum6.setOnClickListener { viewModel.numero(6) }
        binding.btnNum7.setOnClickListener { viewModel.numero(7) }
        binding.btnNum8.setOnClickListener { viewModel.numero(8) }
        binding.btnNum9.setOnClickListener { viewModel.numero(9) }


        // Operaciones

        binding.btnSumar.setOnClickListener { viewModel.operacion("+") }
        binding.btnRestar.setOnClickListener { viewModel.operacion("-") }
        binding.btnMultiplicar.setOnClickListener { viewModel.operacion("*") }
        binding.btnDividir.setOnClickListener { viewModel.operacion("/") }

        // CLEAR Y RESULTADO

        binding.btnBorrar.setOnClickListener { viewModel.clear() }
        binding.btnMostrarResultado.setOnClickListener { viewModel.igual() }
    }
}
