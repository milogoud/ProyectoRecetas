package com.julian.recetasappfinal.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.julian.recetasappfinal.R
import com.julian.recetasappfinal.databinding.FragmentPostresBinding
import com.julian.recetasappfinal.model.Receta
import com.julian.recetasappfinal.view.adapters.RecetasAdapter

class PostresFragment : Fragment() {
    private var _binding: FragmentPostresBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // **Inicializamos `binding` correctamente**
        _binding = FragmentPostresBinding.inflate(inflater, container, false)

        // **Configurar RecyclerView SOLO después de inicializar `binding`**
        setupRecyclerView()

        // **Botón "Volver" correctamente asignado**
        binding.btnVolver.setOnClickListener {
            requireActivity().finish()
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        val listaRecetas = listOf(
            Receta(getString(R.string.tarta_fresa), getString(R.string.desc_tarta_fresa)),
            Receta(getString(R.string.flan_vainilla), getString(R.string.desc_flan_vainilla)),
            Receta(getString(R.string.mousse_chocolate), getString(R.string.desc_mousse_chocolate)),
            Receta(getString(R.string.cheesecake_frutos), getString(R.string.desc_cheesecake_frutos)),
            Receta(getString(R.string.brownie_chocolate), getString(R.string.desc_brownie_chocolate)),
            Receta(getString(R.string.creme_brulee), getString(R.string.desc_creme_brulee)),
            Receta(getString(R.string.helado_pistacho), getString(R.string.desc_helado_pistacho)),
            Receta(getString(R.string.churros_chocolate), getString(R.string.desc_churros_chocolate)),
            Receta(getString(R.string.tiramisu), getString(R.string.desc_tiramisu)),
            Receta(getString(R.string.pavlova), getString(R.string.desc_pavlova)),
            Receta(getString(R.string.profiteroles), getString(R.string.desc_profiteroles)),
            Receta(getString(R.string.panna_cotta), getString(R.string.desc_panna_cotta)),
            Receta(getString(R.string.pastel_zanahoria), getString(R.string.desc_pastel_zanahoria)),
            Receta(getString(R.string.macarons), getString(R.string.desc_macarons))
        )


        binding.rvRecetas.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecetas.adapter = RecetasAdapter(listaRecetas)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
