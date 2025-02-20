package com.julian.recetasappfinal.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.julian.recetasappfinal.R
import com.julian.recetasappfinal.adapter.RecetasAdapter
import com.julian.recetasappfinal.databinding.FragmentEntrantesBinding
import com.julian.recetasappfinal.databinding.FragmentPrincipalesBinding
import com.julian.recetasappfinal.model.Receta
import com.julian.recetasappfinal.viewmodel.RecetaViewModel

class PrincipalesFragment : Fragment() {
    private var _binding: FragmentPrincipalesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RecetaViewModel
    private lateinit var adapter: RecetasAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrincipalesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(RecetaViewModel::class.java)

        // Configurar RecyclerView
        adapter = RecetasAdapter()
        binding.rvRecetas.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecetas.adapter = adapter

        // Observar los cambios en los datos de Firebase y filtrar por tipo "Principal"
        viewModel.recetasLiveData.observe(viewLifecycleOwner) { recetas ->
            val principales = recetas.filter { it.tipo == "Principal" } // Filtrar solo platos principales
            adapter.actualizarLista(principales)
        }


        binding.btnAgregarReceta?.setOnClickListener {
            // Insertar 5 recetas de platos principales
            viewModel.agregarReceta(
                nombre = "Lasaña",
                descripcion = "Lasaña de carne y queso",
                tipo = "Principal",
                imagenResId = R.drawable.lasana
            )
            viewModel.agregarReceta(
                nombre = "Pasta carbonara",
                descripcion = "Pasta con salsa carbonara",
                tipo = "Principal",
                imagenResId = R.drawable.pasta
            )
            viewModel.agregarReceta(
                nombre = "Pollo al curry",
                descripcion = "Pollo con salsa de curry y arroz",
                tipo = "Principal",
                imagenResId = R.drawable.curry
            )
            viewModel.agregarReceta(
                nombre = "Paella",
                descripcion = "Paella de mariscos",
                tipo = "Principal",
                imagenResId = R.drawable.paella
            )
            viewModel.agregarReceta(
                nombre = "Hamburguesa",
                descripcion = "Hamburguesa con queso y vegetales",
                tipo = "Principal",
                imagenResId = R.drawable.burger
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
