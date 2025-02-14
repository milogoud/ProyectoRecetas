package com.julian.recetasappfinal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.julian.recetasappfinal.databinding.ActivityRecetasBinding

class RecetasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecetasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecetasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el ViewPager2 con el adaptador
        val adapter = RecetasPagerAdapter(this)
        binding.viewPager.adapter = adapter

        // Conectar TabLayout con ViewPager2
        val tabTitles = arrayOf("Postres", "Entrantes", "Principales")
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
        // Recibir la categoría y cambiar la pestaña activa
        val categoria = intent.getStringExtra("categoria")
        val tabIndex = when (categoria) {
            "Postres" -> 3
            "Entrantes" -> 1
            "Platos Principales" -> 2
            else -> 0 // Por defecto a Postres
        }
        binding.viewPager.setCurrentItem(tabIndex, false)
    }
    override fun onResume() {
        super.onResume()
        (this as? BaseActivity)?.recargarFragmentos()
    }

}
