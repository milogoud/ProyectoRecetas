package com.julian.recetasappfinal.view.activities

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.julian.recetasappfinal.R
import com.julian.recetasappfinal.databinding.ActivityPreferenciasBinding

class PreferenciasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPreferenciasBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        aplicarModoOscuro()
        super.onCreate(savedInstanceState)
        binding = ActivityPreferenciasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("preferencias", MODE_PRIVATE)

        // Cargar estado guardado del modo oscuro
        val isDarkMode = sharedPreferences.getBoolean("modoOscuro", false)
        binding.switchModoOscuro.isChecked = isDarkMode

        // Escuchar cambios en el switch
        binding.switchModoOscuro.setOnCheckedChangeListener { _, isChecked ->
            cambiarModoOscuro(isChecked)
        }

        // Botón Volver
        binding.btnVolver.setOnClickListener {
            finish()
        }
    }


    private fun cambiarModoOscuro(activar: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("modoOscuro", activar)
        editor.apply()

        //Aplicar el tema globalmente sin forzar nombres específicos
        if (activar) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        //Reiniciar la actividad para aplicar el cambio
        recreate()
    }

    private fun aplicarModoOscuro() {
        //Aplicar el tema dinámico basado en el sistema
        setTheme(R.style.Theme_RecetasAppFinal)
    }

}
