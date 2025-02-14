package com.julian.recetasappfinal

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.julian.recetasappfinal.databinding.ActivityAcercaDeBinding

class AcercaDeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAcercaDeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityAcercaDeBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}