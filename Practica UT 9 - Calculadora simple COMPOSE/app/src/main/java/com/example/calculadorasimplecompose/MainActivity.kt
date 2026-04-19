package com.example.calculadorasimplecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculadorasimplecompose.ui.theme.CalculadoraSimpleComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraSimpleComposeTheme() {
                CalculadoraComposableMVVM()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaginaPrincipalYUnica() {
    CalculadoraSimpleComposeTheme() {
        CalculadoraComposableMVVM()
    }
}