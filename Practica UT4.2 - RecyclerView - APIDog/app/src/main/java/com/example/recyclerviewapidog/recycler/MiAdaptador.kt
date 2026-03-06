package com.example.recyclerviewapidog.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerviewapidog.R
import com.example.recyclerviewapidog.model.DogRespuesta

class MiAdaptador(private var misFotos: DogRespuesta) : RecyclerView.Adapter<MiVista>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiVista {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_perro, parent, false)
        return MiVista(view)
    }

    override fun onBindViewHolder(
        holder: MiVista,
        position: Int
    ) {
        val urlFoto = misFotos.message?.get(position)

        Glide.with(holder.itemView.context).load(urlFoto).into(holder.ivPerro)
    }

    override fun getItemCount(): Int {
        return misFotos.message?.size ?: 0
    }


}