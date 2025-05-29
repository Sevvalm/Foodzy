package com.example.foodzy.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.foodzy.databinding.FragmentYemekDetayBinding
import com.example.foodzy.ui.viewmodel.YemekDetayViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Hilt için gerekli
class YemekDetayFragment : Fragment() {
    private lateinit var binding: FragmentYemekDetayBinding
    private val viewModel: YemekDetayViewModel by viewModels() // ViewModel'i Hilt ile enjekte et
    private val args: YemekDetayFragmentArgs by navArgs()

    private var currentNum = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentYemekDetayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val gelenYemek = args.yemek

        binding.textViewYemekAdiDetay.text = gelenYemek.yemek_adi
        binding.textViewFiyatDetay.text = "${gelenYemek.yemek_fiyat} ₺"
        binding.textViewUrunAdetDetay.text = currentNum.toString()

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemek_resim_adi}"
        Glide.with(requireContext())
            .load(url)
            .override(600, 600)
            .centerCrop()
            .into(binding.imageViewYemekDetay)

            binding.buttonArttir.setOnClickListener {
                currentNum += 1
                binding.textViewUrunAdetDetay.text = currentNum.toString()
            }
            binding.buttonAzalt.setOnClickListener {
                if (currentNum > 1) {
                    currentNum -= 1
                    binding.textViewUrunAdetDetay.text = currentNum.toString()
                }
            }

        binding.buttonSepeteEkleDetay.setOnClickListener {
            Snackbar.make(it,"${gelenYemek.yemek_adi} sepete eklendi", Snackbar.LENGTH_SHORT).show()

            val yemek_adi = gelenYemek.yemek_adi
            val yemek_resim_adi = gelenYemek.yemek_resim_adi
            val yemek_Fiyat = gelenYemek.yemek_fiyat.toInt()
            val yemek_Urun_Adet =binding.textViewUrunAdetDetay.text.toString().toInt()

            viewModel.sepeteEkle(yemek_adi,yemek_resim_adi,yemek_Fiyat,yemek_Urun_Adet)
        }


    }
}