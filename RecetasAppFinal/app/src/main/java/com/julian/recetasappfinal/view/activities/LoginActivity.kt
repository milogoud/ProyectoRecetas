package com.julian.recetasappfinal.view.activities

import UsuarioRepository
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.julian.recetasappfinal.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var usuarioRepository: UsuarioRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usuarioRepository = UsuarioRepository() // Instancia del repositorio

        // Botón para iniciar sesión
        binding.btnLogin.setOnClickListener {
            val usuario = binding.etUsuario.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (usuario.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                verificarUsuario(usuario, password)
            }
        }

        // Botón para ir a RegistroActivity
        binding.btnRegistrar.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
    }

    private fun verificarUsuario(usuario: String, password: String) {
        usuarioRepository.obtenerUsuarios().observe(this) { listaUsuarios ->
            if (listaUsuarios.isNullOrEmpty()) {
                Toast.makeText(this, "No se encontraron usuarios en la base de datos", Toast.LENGTH_SHORT).show()
                return@observe
            }

            val usuarioEncontrado = listaUsuarios.find {
                it.usuario?.trim() == usuario.trim() && it.password?.trim() == password.trim()
            }

            if (usuarioEncontrado != null) {
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Activity1::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }


}
