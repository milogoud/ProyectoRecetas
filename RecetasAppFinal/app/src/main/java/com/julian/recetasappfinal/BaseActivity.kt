package com.julian.recetasappfinal

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        aplicarModoOscuro() // Aplicar el tema antes de cargar la UI
        super.onCreate(savedInstanceState)
    }

    private fun aplicarModoOscuro() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("preferencias", MODE_PRIVATE)
        val isDarkMode = sharedPreferences.getBoolean("modoOscuro", false)

        if (isDarkMode) {
            setTheme(R.style.Theme_RecetasAppFinal_Dark)
        } else {
            setTheme(R.style.Theme_RecetasAppFinal_Light)
        }
    }
    fun recargarFragmentos() {
        val fragmentManager = supportFragmentManager
        for (fragment in fragmentManager.fragments) {
            fragmentManager.beginTransaction().detach(fragment).attach(fragment).commit()
        }
    }
}
