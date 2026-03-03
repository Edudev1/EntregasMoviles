package com.example.recyclerviewcolores.model

class ColorModel {

    var colores = mutableListOf(
        Color("Rojo",        "#FFF44336"),
        Color("Rosa",        "#FFE91E63"),
        Color("Morado",      "#FF9C27B0"),
        Color("Morado Oscuro","#FF673AB7"),
        Color("Indigo",      "#FF3F51B5"),
        Color("Azul",        "#FF2196F3"),
        Color("Azul Claro",  "#FF03A9F4"),
        Color("Cian",        "#FF00BCD4"),
        Color("Turquesa",    "#FF009688"),
        Color("Verde",       "#FF4CAF50"),
        Color("Verde Claro", "#FF8BC34A"),
        Color("Lima",        "#FFCDDC39"),
        Color("Amarillo",    "#FFFFEB3B"),
        Color("Ámbar",       "#FFFFC107"),
        Color("Naranja",     "#FFFF9800"),
        Color("Naranja Oscuro","#FFFF5722"),
        Color("Marrón",      "#FF795548"),
        Color("Gris",        "#FF9E9E9E"),
        Color("Gris Oscuro", "#FF616161"),
        Color("Gris Claro",  "#FFE0E0E0"),
        Color("Negro",       "#FF000000")
    )

    suspend fun retornarListaColores(): Datos {
        return Datos("ok", colores)
    }
}