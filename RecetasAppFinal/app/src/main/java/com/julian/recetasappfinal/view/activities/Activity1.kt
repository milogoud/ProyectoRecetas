package com.julian.recetasappfinal.view.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.julian.recetasappfinal.R
import com.julian.recetasappfinal.databinding.Activity1Binding

class Activity1 : AppCompatActivity() {
    private lateinit var binding: Activity1Binding
    private var isFirstSelection = true // Variable para controlar la primera selección

    override fun onCreate(savedInstanceState: Bundle?) {
        // Aplicar el modo oscuro antes de crear la UI
        val sharedPreferences = getSharedPreferences("preferencias", MODE_PRIVATE)
        val isDarkMode = sharedPreferences.getBoolean("modoOscuro", false)

        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        super.onCreate(savedInstanceState) // 💡 Esto debe ir después del cambio de tema

        // Configurar ViewBinding
        binding = Activity1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar la toolbar como action bar
        setSupportActionBar(binding.toolbar)

        // Configurar el Spinner
        val categoriasPlatos = resources.getStringArray(R.array.categorias_platos)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categoriasPlatos)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter

        // Manejar la selección del Spinner
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (isFirstSelection) {
                    isFirstSelection = false
                    return
                }

                val seleccion = parent?.getItemAtPosition(position).toString()
                Toast.makeText(this@Activity1, "Seleccionaste: $seleccion", Toast.LENGTH_SHORT).show()

                // Abrir RecetasActivity y pasar la categoría seleccionada
                val intent = Intent(this@Activity1, RecetasActivity::class.java)
                intent.putExtra("categoria", seleccion)
                startActivity(intent)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    // Toolbar (Menú)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_preferencias -> {
                val intent = Intent(this, PreferenciasActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_web -> {
                val url = "https://www.google.com"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
                return true
            }
            R.id.action_acerca_de -> {
                val intent = Intent(this, AcercaDeActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
