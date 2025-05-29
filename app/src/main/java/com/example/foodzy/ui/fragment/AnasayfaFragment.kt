package com.example.foodzy.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.foodzy.R
import com.example.foodzy.databinding.FragmentAnasayfaBinding
import com.example.foodzy.ui.adapter.YemeklerAdapter
import com.example.foodzy.ui.viewmodel.AnasayfaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnasayfaFragment : Fragment() {
    private var _binding: FragmentAnasayfaBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AnasayfaViewModel by viewModels()
    private lateinit var yemeklerAdapter: YemeklerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnasayfaBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        yemeklerAdapter = YemeklerAdapter(requireContext(), emptyList(), viewModel)
        binding.yemeklerRv.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            //layoutManager = LinearLayoutManager(requireContext())
            adapter = yemeklerAdapter

        }
        observeViewModel()

    }
    private fun observeViewModel() {
        viewModel.yemeklerListesi.observe(viewLifecycleOwner) { liste ->
            yemeklerAdapter.updateList(liste ?: emptyList())
        }
    }
    override fun onResume() {
        super.onResume()
        viewModel.yemekleriYukle()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}