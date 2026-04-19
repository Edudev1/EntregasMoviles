package com.example.calculadorasimplecompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CalculadoraComposableMVVM() {

    val miViewModel: MiViewModel = viewModel()

    var numero1 by rememberSaveable { mutableStateOf("") }
    var numero2 by rememberSaveable { mutableStateOf("") }

    val resultado by miViewModel.resultado.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp, start = 20.dp, end = 20.dp)
                .background(Color.Green),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Text(
                text = "Calculadora",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.DarkGray)
                    .padding(10.dp),
                color = Color.White,
                textAlign = TextAlign.Center
            )

            TextField(
                value = numero1,
                onValueChange = {
                    numero1 = it
                },
                label = { Text("Número 1") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )

            TextField(
                value = numero2,
                onValueChange = {
                    numero2 = it
                },
                label = { Text("Número 2") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = {
                    miViewModel.sumar(numero1, numero2)
                }) {
                    Text("+")
                }

                Button(onClick = {
                    miViewModel.restar(numero1, numero2)
                }) {
                    Text("-")
                }

                Button(onClick = {
                    miViewModel.multiplicar(numero1, numero2)
                }) {
                    Text("*")
                }
                Button(onClick = {
                    miViewModel.dividir(numero1, numero2)
                }) {
                    Text("/")
                }
            }
            Text(
                text = "Resultado: $resultado",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .background(Color.LightGray)
                    .padding(10.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}