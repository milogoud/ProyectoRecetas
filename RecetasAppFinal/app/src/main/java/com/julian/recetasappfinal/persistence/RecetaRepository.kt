import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.julian.recetasappfinal.model.Receta

class RecetaRepository {

    private var databaseReference: DatabaseReference
    private val URL_REFERENCIA_DATABASE = "https://recetasapp-da474-default-rtdb.europe-west1.firebasedatabase.app/"
    private val _recetasLiveData = MutableLiveData<List<Receta>>()

    init {
        databaseReference = FirebaseDatabase.getInstance(URL_REFERENCIA_DATABASE).reference
        cargarRecetas()
    }

    private fun cargarRecetas() {
        databaseReference.child("receta").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listaRecetas = mutableListOf<Receta>()
                for (childSnapshot in snapshot.children) {
                    val receta = childSnapshot.getValue(Receta::class.java)
                    receta?.let { listaRecetas.add(it) }
                }
                _recetasLiveData.postValue(listaRecetas)
            }

            override fun onCancelled(error: DatabaseError) {
                println("Error al obtener datos: ${error.message}")
            }
        })
    }

    fun obtenerRecetas(): LiveData<List<Receta>> {
        return _recetasLiveData
    }

    fun agregarReceta(receta: Receta) {
        val id = databaseReference.child("receta").push().key // Generar ID
        receta.id = id
        databaseReference.child("receta").child(id!!).setValue(receta)
            .addOnSuccessListener {

            }
            .addOnFailureListener {
                println("Error al agregar receta: ${it.message}")
            }
    }
}