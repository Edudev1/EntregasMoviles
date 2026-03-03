package com.example.recyclerviewcolores.recycler

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewcolores.R
import com.example.recyclerviewcolores.model.Datos

class MiAdaptador(var misDatos: Datos) : RecyclerView.Adapter<MiVista>() {

    var posicionSeleccionada = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MiVista {
        var miVista = LayoutInflater.from(parent.context).inflate(R.layout.my_row, parent, false)

        return MiVista(miVista)
    }

    override fun onBindViewHolder(
        holder: MiVista,
        position: Int
    ) {
        holder.miFila.setBackgroundColor(misDatos.lista.get(position).codigo.toColorInt())
        holder.miTextoNom.text = misDatos.lista.get(position).color
        holder.miTextoCod.text = misDatos.lista.get(position).codigo

        if (position == posicionSeleccionada) {
            holder.miFila.setBackgroundColor(Color.WHITE)
            holder.miTextoCod.setTextColor(misDatos.lista.get(position).codigo.toColorInt())
            holder.miTextoNom.setTextColor(misDatos.lista.get(position).codigo.toColorInt())
        } else {
            holder.miFila.setBackgroundColor(misDatos.lista.get(position).codigo.toColorInt())
            holder.miTextoCod.setTextColor(Color.WHITE)
            holder.miTextoNom.setTextColor(Color.WHITE)
        }
        holder.miFila.setOnClickListener {
            notifyItemChanged(posicionSeleccionada)
            posicionSeleccionada = position
            notifyItemChanged(posicionSeleccionada)
        }

    }

    override fun getItemCount(): Int {
        return misDatos.lista.size
    }
}