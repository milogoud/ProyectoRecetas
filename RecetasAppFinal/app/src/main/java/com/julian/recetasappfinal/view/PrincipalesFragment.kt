package com.julian.recetasappfinal.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.julian.recetasappfinal.R
import com.julian.recetasappfinal.databinding.FragmentPrincipalesBinding
import com.julian.recetasappfinal.model.Receta
import com.julian.recetasappfinal.view.adapters.RecetasAdapter

class PrincipalesFragment : Fragment() {
    private var _binding: FragmentPrincipalesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrincipalesBinding.inflate(inflater, container, false)
        // Lista de recetas de platos principales
        val listaRecetas = listOf(
            Receta(getString(R.string.lasagna_carne), getString(R.string.desc_lasagna_carne)),
            Receta(getString(R.string.pollo_curry), getString(R.string.desc_pollo_curry)),
            Receta(getString(R.string.bacalao_vizcaina), getString(R.string.desc_bacalao_vizcaina)),
            Receta(getString(R.string.paella_valenciana), getString(R.string.desc_paella_valenciana)),
            Receta(getString(R.string.cochinita_pibil), getString(R.string.desc_cochinita_pibil)),
            Receta(getString(R.string.ratatouille), getString(R.string.desc_ratatouille)),
            Receta(getString(R.string.filete_wellington), getString(R.string.desc_filete_wellington)),
            Receta(getString(R.string.cordero_horno), getString(R.string.desc_cordero_horno)),
            Receta(getString(R.string.risotto_setas), getString(R.string.desc_risotto_setas)),
            Receta(getString(R.string.entrecot_parrilla), getString(R.string.desc_entrecot_parrilla)),
            Receta(getString(R.string.pasta_alfredo), getString(R.string.desc_pasta_alfredo)),
            Receta(getString(R.string.salmon_miel_mostaza), getString(R.string.desc_salmon_miel_mostaza)),
            Receta(getString(R.string.goulash), getString(R.string.desc_goulash)),
            Receta(getString(R.string.tacos_pastor), getString(R.string.desc_tacos_pastor))
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
