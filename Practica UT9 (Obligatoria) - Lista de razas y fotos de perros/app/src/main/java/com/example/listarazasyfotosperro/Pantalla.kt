package com.example.listarazasyfotosperro

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun ListaPerros(navController: NavController) {
    val myViewModel: ListaPerrosViewModel = viewModel()
    val perros by myViewModel.listaRazas.collectAsState()

    LaunchedEffect(Unit) {
        myViewModel.cargarRazas()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3EEF7))
    ) {
        Text(
            text = "Lista de Razas",
            fontSize = 32.sp,
            color = Color.Blue,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            textAlign = TextAlign.Center
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(perros) { razaItem ->

                val textoRaza = if (razaItem.subraza.isEmpty()) {
                    razaItem.raza
                } else {
                    "${razaItem.raza} - ${razaItem.subraza}"
                }

                val subrazaNavegacion = razaItem.subraza.ifEmpty {
                    "sinSubraza"
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFEDE7F6))
                        .clickable {
                            navController.navigate("detalle/${razaItem.raza}/$subrazaNavegacion")
                        }
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = textoRaza,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun DetallePerro(
    raza: String,
    subraza: String,
    navController: NavController
) {
    val myViewModel: ListaPerrosViewModel = viewModel()
    val imagenes by myViewModel.listaImagenes.collectAsState()

    LaunchedEffect(raza, subraza) {
        myViewModel.cargarImagenes(raza, subraza)
    }

    val titulo = if (subraza.isEmpty()) {
        "Fotos de $raza"
    } else {
        "Fotos de $raza - $subraza"
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3EEF7))
    ) {
        item {
            Text(
                text = titulo,
                fontSize = 30.sp,
                color = Color.Blue,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                textAlign = TextAlign.Center
            )
        }

        items(imagenes) { foto ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(Color(0xFFF3EEF7)),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = foto,
                    contentDescription = "Foto de perro",
                    modifier = Modifier.size(120.dp),
                )
            }
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Text(text = "Volver")
                }
            }
        }
    }
}