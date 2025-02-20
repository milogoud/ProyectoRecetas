package com.julian.recetasappfinal.model

data class Receta(
    var id: String? = null,
    var nombre: String? = null,
    var tipo: String? = null, // Nuevo campo para la categoría
    var descripcion: String? = null,
    var imagenResId: Int? = null
)