package com.julian.recetasappfinal.viewmodel

import RecetaRepository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.julian.recetasappfinal.model.Receta


class RecetaViewModel : ViewModel() {
    private val _recetasLiveData = MutableLiveData<List<Receta>>()
    private val repository=RecetaRepository()
    val recetasLiveData: LiveData<List<Receta>> get() = _recetasLiveData

    init {

        getRecetasFromRepository()
    }


    private fun getRecetasFromRepository() {

            repository.obtenerRecetas().observeForever { recetas ->
                if (recetas != null) {
                    _recetasLiveData.postValue(recetas)
                    Log.d("RecetaViewModel", "Recetas obtenidas: $recetas")
                }
            }

    }

    // Obtener recetas desde Firebase
    private fun obtenerRecetas() :List<Receta> {
      return _recetasLiveData.value.orEmpty()
    }

    fun agregarReceta(nombre: String, descripcion: String, tipo: String, imagenResId: Int?) {
        val nuevaReceta = Receta(
            nombre = nombre,
            descripcion = descripcion,
            tipo = tipo,
            imagenResId = imagenResId // Usar imagenResId en lugar de imagenUrl
        )
        repository.agregarReceta(nuevaReceta)
    }
}
