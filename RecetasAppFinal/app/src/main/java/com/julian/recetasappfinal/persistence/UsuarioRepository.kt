import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.julian.recetasappfinal.model.Usuario

class UsuarioRepository {

    private var databaseReference: DatabaseReference
    private val URL_REFERENCIA_DATABASE = "https://recetasapp-da474-default-rtdb.europe-west1.firebasedatabase.app/"
    private val _usuariosLiveData = MutableLiveData<List<Usuario>>()

    init {
        databaseReference = FirebaseDatabase.getInstance(URL_REFERENCIA_DATABASE).reference
        cargarUsuarios()
    }

    private fun cargarUsuarios() {
        databaseReference.child("usuarios").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listaUsuarios = mutableListOf<Usuario>()
                for (childSnapshot in snapshot.children) {
                    val usuario = childSnapshot.getValue(Usuario::class.java)
                    usuario?.let { listaUsuarios.add(it) }
                }
                _usuariosLiveData.postValue(listaUsuarios)
            }

            override fun onCancelled(error: DatabaseError) {
                println("Error al obtener datos: ${error.message}")
            }
        })
    }

    fun obtenerUsuarios(): LiveData<List<Usuario>> {
        return _usuariosLiveData
    }

    fun guardarUsuario(usuario: Usuario) {
        val id = databaseReference.child("usuarios").push().key // Generar ID
        usuario.id = id
        databaseReference.child("usuarios").child(id!!).setValue(usuario)
            .addOnSuccessListener {
            }
            .addOnFailureListener {
                println("Error al agregar usuario: ${it.message}")
            }
    }
}
