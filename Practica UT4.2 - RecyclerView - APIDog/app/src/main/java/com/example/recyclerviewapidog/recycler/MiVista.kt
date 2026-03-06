package com.example.recyclerviewapidog.recycler

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewapidog.R

class MiVista(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ivPerro: ImageView = itemView.findViewById(R.id.ivPerro)
}