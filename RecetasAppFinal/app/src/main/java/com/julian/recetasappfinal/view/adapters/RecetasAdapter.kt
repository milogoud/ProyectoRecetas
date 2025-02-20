package com.julian.recetasappfinal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.julian.recetasappfinal.R
import com.julian.recetasappfinal.databinding.ItemRecetaBinding
import com.julian.recetasappfinal.model.Receta

class RecetasAdapter : RecyclerView.Adapter<RecetasAdapter.RecetaViewHolder>() {

    private var listaRecetas = listOf<Receta>()

    fun actualizarLista(nuevaLista: List<Receta>) {
        listaRecetas = nuevaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetaViewHolder {
        val binding = ItemRecetaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecetaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecetaViewHolder, position: Int) {
        holder.bind(listaRecetas[position])
    }

    override fun getItemCount(): Int = listaRecetas.size

    class RecetaViewHolder(private val binding: ItemRecetaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(receta: Receta) {
            binding.tvNombreReceta.text = receta.nombre
            binding.tvDescripcionReceta.text = receta.descripcion
            // Asignar la imagen local al ImageView
            if (receta.imagenResId != null) {
                binding.ivImagenReceta?.setImageResource(receta.imagenResId!!)
            } else {
                // Si no hay imagen, mostrar una por defecto
                binding.ivImagenReceta?.setImageResource(R.drawable.paella)
            }
        }
    }
}
