package com.julian.recetasappfinal.view.activities

import UsuarioRepository
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
                Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
