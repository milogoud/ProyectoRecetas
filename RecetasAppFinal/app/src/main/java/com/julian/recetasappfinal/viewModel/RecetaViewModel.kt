package com.julian.recetasappfinal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.julian.recetasappfinal.model.Receta

class RecetaViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val _recetasLiveData = MutableLiveData<List<Receta>>()
    val recetasLiveData: LiveData<List<Receta>> get() = _recetasLiveData

    init {
        obtenerRecetas()
    }

    // Obtener recetas desde Firebase
    private fun obtenerRecetas() {
        db.collection("recetas")
            .addSnapshotListener { snapshot, error ->
                if (error != null || snapshot == null) return@addSnapshotListener

                val listaRecetas = snapshot.documents.mapNotNull { doc ->
                    doc.toObject(Receta::class.java)
                }
                _recetasLiveData.value = listaRecetas
            }
    }

    // Agregar una nueva receta a Firebase
    fun agregarReceta(nombre: String, descripcion: String, imagenUrl: String?) {
        val nuevaReceta = Receta(nombre, descripcion, imagenUrl)

        db.collection("recetas")
            .add(nuevaReceta)
            .addOnSuccessListener { /* Éxito */ }
            .addOnFailureListener { /* Manejo de error */ }
    }
}
