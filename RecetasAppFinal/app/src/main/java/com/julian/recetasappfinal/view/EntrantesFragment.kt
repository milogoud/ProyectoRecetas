package com.julian.recetasappfinal.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.julian.recetasappfinal.R
import com.julian.recetasappfinal.databinding.FragmentEntrantesBinding
import com.julian.recetasappfinal.model.Receta
import com.julian.recetasappfinal.view.adapters.RecetasAdapter

class EntrantesFragment : Fragment() {
    private var _binding: FragmentEntrantesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEntrantesBinding.inflate(inflater, container, false)
        // Lista de recetas de entrantes
        val listaRecetas = listOf(
            Receta(getString(R.string.ensalada_cesar), getString(R.string.desc_ensalada_cesar)),
            Receta(getString(R.string.sopa_tomate), getString(R.string.desc_sopa_tomate)),
            Receta(getString(R.string.carpaccio_ternera), getString(R.string.desc_carpaccio_ternera)),
            Receta(getString(R.string.bruschetta_tomate), getString(R.string.desc_bruschetta_tomate)),
            Receta(getString(R.string.hummus_pita), getString(R.string.desc_hummus_pita)),
            Receta(getString(R.string.rollitos_primavera), getString(R.string.desc_rollitos_primavera)),
            Receta(getString(R.string.gazpacho_andaluz), getString(R.string.desc_gazpacho_andaluz)),
            Receta(getString(R.string.patatas_bravas), getString(R.string.desc_patatas_bravas)),
            Receta(getString(R.string.croquetas_jamon), getString(R.string.desc_croquetas_jamon)),
            Receta(getString(R.string.ceviche_pescado), getString(R.string.desc_ceviche_pescado)),
            Receta(getString(R.string.champiñones_rellenos), getString(R.string.desc_champiñones_rellenos)),
            Receta(getString(R.string.queso_camembert), getString(R.string.desc_queso_camembert)),
            Receta(getString(R.string.tostadas_aguacate), getString(R.string.desc_tostadas_aguacate)),
            Receta(getString(R.string.baba_ganoush), getString(R.string.desc_baba_ganoush))
        )


        // Configurar RecyclerView
        binding.rvRecetas.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecetas.adapter = RecetasAdapter(listaRecetas)
        binding.btnVolver.setOnClickListener {
            requireActivity().finish()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
