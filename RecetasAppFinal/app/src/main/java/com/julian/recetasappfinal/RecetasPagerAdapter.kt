package com.julian.recetasappfinal

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class RecetasPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3 // Tenemos 3 categorías: Postres, Entrantes, Principales

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PrincipalesFragment()
            1 -> EntrantesFragment()
            2 -> PostresFragment()
            else -> throw IllegalStateException("Posición inválida: $position")
        }
    }
}
