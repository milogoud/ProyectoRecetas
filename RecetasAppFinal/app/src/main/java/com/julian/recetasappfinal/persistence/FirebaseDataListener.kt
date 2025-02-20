package com.julian.recetasappfinal.persistence

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.julian.recetasappfinal.model.Receta

class FirebaseDataListener (var dataList: MutableLiveData<List<Receta>>): ValueEventListener {
    override fun onDataChange(snapshot: DataSnapshot) {
        val recetasList = mutableListOf<Receta>()
        for(dataSnapshot in snapshot.children){
            val receta = dataSnapshot.getValue(Receta::class.java)
            if(receta != null){
                recetasList.add(receta)
            }
        }
        dataList.postValue(recetasList)
    }

    override fun onCancelled(error: DatabaseError) {
        TODO("Not yet implemented")
    }

}