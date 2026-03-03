package com.example.recyclerviewcolores.recycler

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewcolores.R
class MiVista(miFila : View) : RecyclerView.ViewHolder(miFila) {

    var miFila = miFila.findViewById<LinearLayout>(R.id.llFila)

    var miTextoCod = miFila.findViewById<TextView>(R.id.tvCodigo)

    var miTextoNom = miFila.findViewById<TextView>(R.id.tvColor)
}