package com.julian.recetasappfinal.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.julian.recetasappfinal.model.Receta
import com.julian.recetasappfinal.persistence.RecetaRepository

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

    // Agregar una nueva receta a Firebase
    fun agregarReceta(nombre: String, descripcion: String, imagenUrl: String?) {
        val nuevaReceta = Receta(nombre, descripcion, imagenUrl)
        repository.agregarReceta(nuevaReceta)


    }
}
