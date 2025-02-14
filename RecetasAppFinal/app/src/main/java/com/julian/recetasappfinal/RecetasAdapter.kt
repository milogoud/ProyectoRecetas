package com.julian.recetasappfinal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.julian.recetasappfinal.databinding.ItemRecetaBinding

class RecetasAdapter(private val recetas: List<Receta>) :
    RecyclerView.Adapter<RecetasAdapter.RecetaViewHolder>() {

    inner class RecetaViewHolder(private val binding: ItemRecetaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(receta: Receta) {
            binding.tvNombreReceta.text = receta.nombre
            binding.tvDescripcionReceta.text = receta.descripcion
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetaViewHolder {
        val binding = ItemRecetaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecetaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecetaViewHolder, position: Int) {
        holder.bind(recetas[position])
    }

    override fun getItemCount(): Int = recetas.size
}
