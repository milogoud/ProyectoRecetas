package com.julian.recetasappfinal.view

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
import com.julian.recetasappfinal.viewmodel.RecetaViewModel

class EntrantesFragment : Fragment() {

    private var _binding: FragmentEntrantesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RecetaViewModel
    private lateinit var adapter: RecetasAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEntrantesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(RecetaViewModel::class.java)

        // Configurar RecyclerView
        adapter = RecetasAdapter()
        binding.rvRecetas.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecetas.adapter = adapter

        // Observar los cambios en los datos de Firebase y filtrar por tipo "Entrante"
        viewModel.recetasLiveData.observe(viewLifecycleOwner) { recetas ->
            val entrantes = recetas.filter { it.tipo == "Entrante" } // Filtrar solo entrantes
            adapter.actualizarLista(entrantes)
        }
/*
        binding.btnAgregarReceta?.setOnClickListener {
            // Insertar 5 recetas de entrantes
            viewModel.agregarReceta(
                nombre = "Ensalada César",
                descripcion = "Ensalada con pollo, crutones y aderezo",
                tipo = "Entrante",
                imagenResId = R.drawable.ensaladacesar
            )
            viewModel.agregarReceta(
                nombre = "Bruschetta",
                descripcion = "Pan tostado con tomate, albahaca y aceite de oliva",
                tipo = "Entrante",
                imagenResId = R.drawable.bruschetta
            )
            viewModel.agregarReceta(
                nombre = "Sopa de calabaza",
                descripcion = "Sopa cremosa de calabaza",
                tipo = "Entrante",
                imagenResId = R.drawable.calabazin
            )
            viewModel.agregarReceta(
                nombre = "Croquetas de jamón",
                descripcion = "Croquetas caseras de jamón",
                tipo = "Entrante",
                imagenResId = R.drawable.croquetasjamon
            )
            viewModel.agregarReceta(
                nombre = "Empanadillas",
                descripcion = "Empanadillas de carne o verduras",
                tipo = "Entrante",
                imagenResId = R.drawable.empanadillas
            )
        }*/
    }

    

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
