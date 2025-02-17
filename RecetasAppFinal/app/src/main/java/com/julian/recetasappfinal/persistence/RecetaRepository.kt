package com.julian.recetasappfinal.persistence

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.julian.recetasappfinal.model.Receta

class RecetaRepository {

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance()
        .getReference("recetas") //

    fun agregarReceta(receta: Receta) {
        val id = databaseReference.push().key // Generar ID
        receta.id = id
        databaseReference.child(id!!).setValue(receta)
    }

    fun obtenerRecetas(): LiveData<List<Receta>> {
        val recetasLiveData = MutableLiveData<List<Receta>>()

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listaRecetas = mutableListOf<Receta>()
                for (childSnapshot in snapshot.children) {
                    val receta = childSnapshot.getValue(Receta::class.java)
                    receta?.let { listaRecetas.add(it) }
                }
                recetasLiveData.postValue(listaRecetas)
            }

            override fun onCancelled(error: DatabaseError) {
                println("Error al obtener datos: ${error.message}")
            }
        })
        return recetasLiveData
    }
}
