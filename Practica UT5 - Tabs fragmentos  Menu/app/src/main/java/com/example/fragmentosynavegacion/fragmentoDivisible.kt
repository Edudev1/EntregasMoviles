package com.example.fragmentosynavegacion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.fragmentosynavegacion.viewmodel.MainViewModel
import androidx.fragment.app.viewModels

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_TEXTO = "texto"
private const val ARG_NUM = "num"

/**
 * A simple [Fragment] subclass.
 * Use the [fragmentoDivisible.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragmentoDivisible : Fragment() {

    // TODO: Rename and change types of parameters
    private var texto: String? = null
    private var num: Int? = null

    private lateinit var viewModel: MainViewModel

    private lateinit var cb2: CheckBox
    private lateinit var cb3: CheckBox
    private lateinit var cb5: CheckBox
    private lateinit var cb10: CheckBox
    private lateinit var cbNinguno: CheckBox
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
        return inflater.inflate(R.layout.fragment_fragmento_divisible, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        cb2 = view.findViewById(R.id.cb2)
        cb3 = view.findViewById(R.id.cb3)
        cb5 = view.findViewById(R.id.cb5)
        cb10 = view.findViewById(R.id.cb10)
        cbNinguno = view.findViewById(R.id.cbNinguno)
        btnValidar = view.findViewById(R.id.btnValidarDivisible)
        tvResultado = view.findViewById(R.id.tvResultadoDivisible)

        viewModel.datos.observe(viewLifecycleOwner) { datos ->

            when (datos.estado) {

                1 -> {
                    tvResultado.text = "Pendiente"
                    tvResultado.setTextColor(resources.getColor(android.R.color.holo_blue_dark, null))

                    cb2.isChecked = false
                    cb3.isChecked = false
                    cb5.isChecked = false
                    cb10.isChecked = false
                    cbNinguno.isChecked = false
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

            val opciones = mutableListOf<String>()

            if (cb2.isChecked) opciones.add("2")
            if (cb3.isChecked) opciones.add("3")
            if (cb5.isChecked) opciones.add("5")
            if (cb10.isChecked) opciones.add("10")
            if (cbNinguno.isChecked) opciones.add("ninguno")

            viewModel.validarDivisible(opciones)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param texto Texto que se puede mostrar en el fragmento.
         * @param num Número generado.
         * @return A new instance of fragment fragmentoDivisible.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(texto: String, num: Int) =
            fragmentoDivisible().apply {
                arguments = Bundle().apply {
                    putString(ARG_TEXTO, texto)
                    putInt(ARG_NUM, num)
                }
            }
    }
}