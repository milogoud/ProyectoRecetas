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
import com.julian.recetasappfinal.databinding.FragmentPostresBinding
import com.julian.recetasappfinal.databinding.FragmentPrincipalesBinding
import com.julian.recetasappfinal.model.Receta
import com.julian.recetasappfinal.viewmodel.RecetaViewModel

class PostresFragment : Fragment() {
    private var _binding: FragmentPostresBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RecetaViewModel
    private lateinit var adapter: RecetasAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostresBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(RecetaViewModel::class.java)

        // Configurar RecyclerView
        adapter = RecetasAdapter()
        binding.rvRecetas.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecetas.adapter = adapter

        //filtrar por postres
        viewModel.recetasLiveData.observe(viewLifecycleOwner) { recetas ->
            val postres = recetas.filter { it.tipo == "Postre" } // Filtrar solo postres
            adapter.actualizarLista(postres)
        }

        binding.btnAgregarReceta?.setOnClickListener {
            // Insertar 5 recetas de postres
            viewModel.agregarReceta(
                nombre = "Tarta de manzana",
                descripcion = "Una deliciosa tarta de manzana",
                tipo = "Postre",
                imagenResId = R.drawable.tartamanza
            )
            viewModel.agregarReceta(
                nombre = "Flan",
                descripcion = "Flan de huevo tradicional",
                tipo = "Postre",
                imagenResId = R.drawable.flanvainilla
            )
            viewModel.agregarReceta(
                nombre = "Helado",
                descripcion = "Helado de vainilla con toppings",
                tipo = "Postre",
                imagenResId = R.drawable.helado
            )
            viewModel.agregarReceta(
                nombre = "Brownie",
                descripcion = "Brownie de chocolate con nueces",
                tipo = "Postre",
                imagenResId = R.drawable.brownie
            )
            viewModel.agregarReceta(
                nombre = "Tiramisú",
                descripcion = "Postre italiano con café y cacao",
                tipo = "Postre",
                imagenResId = R.drawable.tiramisu
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
