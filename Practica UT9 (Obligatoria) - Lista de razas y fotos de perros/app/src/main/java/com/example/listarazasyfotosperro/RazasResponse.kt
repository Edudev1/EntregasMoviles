package com.example.listarazasyfotosperro

data class RazasResponse(
    val message: Map<String, List<String>>,
    val status: String
)