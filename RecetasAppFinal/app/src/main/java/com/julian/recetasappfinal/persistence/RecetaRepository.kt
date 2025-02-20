package com.julian.recetasappfinal.persistence

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.google.firebase.database.ValueEventListener
import com.julian.recetasappfinal.model.Receta
import java.net.URL

class RecetaRepository {

    private var databaseReference: DatabaseReference
    private val URL_REFERENCIA_DATABASE="https://recetasapp-da474-default-rtdb.europe-west1.firebasedatabase.app/"
    init {
        databaseReference=FirebaseDatabase.getInstance(URL_REFERENCIA_DATABASE).reference
    }

    fun agregarReceta(receta: Receta) {
        val id = databaseReference.child("receta").push().key // Generar ID
        receta.id = id
        databaseReference.child("receta").child(id!!).setValue(receta)
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
