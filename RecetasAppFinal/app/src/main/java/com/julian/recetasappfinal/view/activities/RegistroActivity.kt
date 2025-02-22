package com.julian.recetasappfinal.view.activities

import UsuarioRepository
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.julian.recetasappfinal.R
import com.julian.recetasappfinal.databinding.ActivityRegistroBinding
import com.julian.recetasappfinal.model.Usuario

class RegistroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroBinding
    private lateinit var usuarioRepository: UsuarioRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usuarioRepository = UsuarioRepository()

        binding.btnRegistrar.setOnClickListener {
            val usuario = binding.etUsuario.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (usuario.isNotEmpty() && password.isNotEmpty()) {
                val nuevoUsuario = Usuario(id = "", usuario = usuario, password = password)
                usuarioRepository.agregarUsuario(nuevoUsuario)
                AlertDialog.Builder(this)
                    .setView(layoutInflater.inflate(R.layout.dialog_success, null))
                    .setPositiveButton(getString(R.string.ok)) { _, _ ->
                        finish() // Cierra la actividad al aceptar
                    }
                    .setCancelable(false) // Evita cerrar el diálogo tocando fuera
                    .show()
            } else {
                Toast.makeText(this, getString(R.string.fill_fields), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
