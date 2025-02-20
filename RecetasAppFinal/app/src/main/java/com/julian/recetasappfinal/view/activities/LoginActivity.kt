package com.julian.recetasappfinal.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.julian.recetasappfinal.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    // Declarar ViewBinding
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar ViewBinding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el botón de inicio de sesión
        binding.btnLogin.setOnClickListener {
            // Obtener el texto de los campos
            val usuario = binding.etUsuario.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            // Validar que los campos no estén vacíos
            if (usuario.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                // Aquí iría la lógica para validar el usuario y la contraseña
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                // Redirigir a la actividad principal
                val intent = Intent(this, Activity1::class.java)
                startActivity(intent)
                finish() // Cerrar la actividad de login
            }
        }

    }
}