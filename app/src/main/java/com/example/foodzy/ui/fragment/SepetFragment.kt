package com.example.foodzy.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodzy.R
import com.example.foodzy.databinding.FragmentAnasayfaBinding
import com.example.foodzy.databinding.FragmentSepetBinding
import com.example.foodzy.ui.adapter.SepetAdapter
import com.example.foodzy.ui.viewmodel.SepetViewModel
import com.example.foodzy.ui.viewmodel.YemekDetayViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SepetFragment : Fragment() {
    private var _binding: FragmentSepetBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SepetViewModel by viewModels()
    private lateinit var sepetAdapter : SepetAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentSepetBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sepetAdapter = SepetAdapter(requireContext(),emptyList(),viewModel)
        binding.sepetRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = sepetAdapter
        }
        observeViewModel()
    }
    private fun observeViewModel(){
        viewModel.sepetListesi.observe(viewLifecycleOwner) { liste ->
            sepetAdapter.updateList(liste ?: emptyList())
        }
    }
    override fun onResume() {
        super.onResume()
        viewModel.sepetiYukle()
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}