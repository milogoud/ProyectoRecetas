package com.julian.recetasappfinal

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.julian.recetasappfinal.databinding.ActivityPreferenciasBinding

class PreferenciasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPreferenciasBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        // Aplicar el tema antes de cargar la UI
        aplicarModoOscuro()

        super.onCreate(savedInstanceState)
        binding = ActivityPreferenciasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("preferencias", MODE_PRIVATE)

        // Configurar switch para modo oscuro
        binding.switchModoOscuro.isChecked = sharedPreferences.getBoolean("modoOscuro", false)
        binding.switchModoOscuro.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean("modoOscuro", isChecked).apply()

            // Reiniciar la app para aplicar el nuevo tema en todas las actividades
            val intent = Intent(this, Activity1::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        // Botón Volver
        binding.btnVolver.setOnClickListener {
            finish()
        }
    }

    private fun aplicarModoOscuro() {
        val isDarkMode = getSharedPreferences("preferencias", MODE_PRIVATE)
            .getBoolean("modoOscuro", false)

        if (isDarkMode) {
            setTheme(R.style.Theme_RecetasAppFinal_Dark) // Aplica el tema oscuro
        } else {
            setTheme(R.style.Theme_RecetasAppFinal_Light) // Aplica el tema claro
        }
    }
}
