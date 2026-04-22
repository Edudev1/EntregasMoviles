package com.example.listarazasyfotosperro

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun navegacion() {
    val miNavController = rememberNavController()

    NavHost(
        navController = miNavController,
        startDestination = "lista"
    ) {
        composable("lista") {
            ListaPerros(miNavController)
        }

        composable(
            route = "detalle/{raza}/{subraza}",
            arguments = listOf(
                navArgument("raza") { type = NavType.StringType },
                navArgument("subraza") { type = NavType.StringType }
            )
        ) {
            val raza = it.arguments?.getString("raza") ?: ""
            val subrazaArgumento = it.arguments?.getString("subraza") ?: ""
            val subraza = if (subrazaArgumento == "sinSubraza") "" else subrazaArgumento

            DetallePerro(raza, subraza, miNavController)
        }
    }
}