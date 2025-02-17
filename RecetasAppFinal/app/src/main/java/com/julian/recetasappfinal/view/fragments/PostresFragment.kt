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

        // Observar los cambios en los datos de Firebase
        viewModel.recetasLiveData.observe(viewLifecycleOwner) { recetas ->
            adapter.actualizarLista(recetas)
        }

        // Agregar receta de prueba
        binding.btnAgregarReceta?.setOnClickListener {
            viewModel.agregarReceta("Nueva Receta", "Descripción de la nueva receta", null)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
