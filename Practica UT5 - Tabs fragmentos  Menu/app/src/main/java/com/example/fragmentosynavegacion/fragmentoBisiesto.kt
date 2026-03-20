package com.example.fragmentosynavegacion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fragmentosynavegacion.viewmodel.MainViewModel
import kotlin.properties.ReadOnlyProperty
import androidx.fragment.app.viewModels

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_TEXTO = "texto"
private const val ARG_NUM = "num"

/**
 * A simple [Fragment] subclass.
 * Use the [fragmentoBisiesto.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragmentoBisiesto : Fragment() {

    // TODO: Rename and change types of parameters
    private var texto: String? = null
    private var num: Int? = null

    private val viewModel: MainViewModel by viewModels()
    private lateinit var rbSi: RadioButton
    private lateinit var rbNo: RadioButton
    private lateinit var btnValidar: Button
    private lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            texto = it.getString(ARG_TEXTO)
            num = it.getInt(ARG_NUM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragmento_bisiesto, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        rbSi = view.findViewById(R.id.rbSi)
        rbNo = view.findViewById(R.id.rbNo)
        btnValidar = view.findViewById(R.id.btnValidarBisiesto)
        tvResultado = view.findViewById(R.id.tvResultadoBisiesto)

        viewModel.datos.observe(viewLifecycleOwner) { datos ->

            when (datos.estado) {

                1 -> {
                    tvResultado.text = "Pendiente"
                    tvResultado.setTextColor(resources.getColor(android.R.color.holo_blue_dark, null))
                    rbSi.isChecked = false
                    rbNo.isChecked = false
                }

                0 -> {
                    tvResultado.text = "Correcto"
                    tvResultado.setTextColor(resources.getColor(android.R.color.holo_green_dark, null))
                }

                -1 -> {
                    tvResultado.text = "Incorrecto"
                    tvResultado.setTextColor(resources.getColor(android.R.color.holo_red_dark, null))
                }
            }
        }

        btnValidar.setOnClickListener {

            val opcion = when {
                rbSi.isChecked -> "SI"
                rbNo.isChecked -> "NO"
                else -> ""
            }

            if (opcion.isNotEmpty()) {
                viewModel.validarBisiesto(opcion)
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param texto Texto que se puede mostrar en el fragmento.
         * @param num Número generado.
         * @return A new instance of fragment fragmentoBisiesto.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(texto: String, num: Int) =
            fragmentoBisiesto().apply {
                arguments = Bundle().apply {
                    putString(ARG_TEXTO, texto)
                    putInt(ARG_NUM, num)
                }
            }
    }
}