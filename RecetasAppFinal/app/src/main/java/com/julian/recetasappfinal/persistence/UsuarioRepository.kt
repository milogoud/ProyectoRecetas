import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.julian.recetasappfinal.model.Usuario

class UsuarioRepository {

    private var databaseReference: DatabaseReference
    private val URL_REFERENCIA_DATABASE = "https://recetasapp-da474-default-rtdb.europe-west1.firebasedatabase.app/"

    init {
        databaseReference = FirebaseDatabase.getInstance(URL_REFERENCIA_DATABASE).reference
    }

    fun guardarUsuario(usuario: Usuario) {
        val id = databaseReference.child("usuarios").push().key // Generar ID
        usuario.id = id
        databaseReference.child("usuarios").child(id!!).setValue(usuario)
    }

    fun obtenerUsuarios(callback: (List<Usuario>) -> Unit) {
        databaseReference.child("usuarios").addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listaUsuarios = mutableListOf<Usuario>()
                for (childSnapshot in snapshot.children) {
                    val usuario = childSnapshot.getValue(Usuario::class.java)
                    usuario?.let { listaUsuarios.add(it) }
                }
                callback(listaUsuarios)
            }

            override fun onCancelled(error: DatabaseError) {
                println("Error al obtener usuarios: ${error.message}")
            }
        })
    }
}