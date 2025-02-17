package com.julian.recetasappfinal.model

data class Receta(
    var id: String? = null,
    var nombre: String? = null,
    var descripcion: String? = null,
    var imagenUrl: String? = null // imagenes en firebase
) {
    constructor() : this(null, null, null, null)
}
