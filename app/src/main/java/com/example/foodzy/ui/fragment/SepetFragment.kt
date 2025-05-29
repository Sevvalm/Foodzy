package com.example.foodzy.ui.fragment

import android.animation.Animator
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodzy.R
import com.example.foodzy.databinding.FragmentAnasayfaBinding
import com.example.foodzy.databinding.FragmentSepetBinding
import com.example.foodzy.ui.adapter.SepetAdapter
import com.example.foodzy.ui.viewmodel.SepetViewModel
import com.example.foodzy.ui.viewmodel.YemekDetayViewModel
import com.example.foodzy.utils.gecisYap
import com.google.android.material.snackbar.Snackbar
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

        binding.buttonSepetOnayla.setOnClickListener {
            binding.animationOverlay.visibility = View.VISIBLE
            binding.lottieAnimation.visibility = View.VISIBLE
            binding.animationMessage.visibility = View.VISIBLE
            binding.buttonSepetOnayla.visibility = View.GONE
            binding.buttonSepetOnayla.isEnabled = false
            binding.lottieAnimation.playAnimation()

            binding.lottieAnimation.addAnimatorListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}

                override fun onAnimationEnd(animation: Animator) {
                    Navigation.gecisYap(it,R.id.sepettenAnasayfaGecis)

                    binding.animationOverlay.visibility = View.GONE
                    binding.lottieAnimation.visibility = View.GONE
                    binding.animationMessage.visibility = View.GONE

                    binding.buttonSepetOnayla.visibility = View.VISIBLE
                    binding.buttonSepetOnayla.isEnabled = true
                }
                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })
        }
    }
    private fun observeViewModel() {
        viewModel.sepetListesi.observe(viewLifecycleOwner) { liste ->
            if (liste.isNullOrEmpty()) {
                Log.e("SepetFragment", "Sepet boş! RecyclerView ve buton gizleniyor, TextView görünüyor.")
                binding.sepetRv.visibility = View.GONE
                binding.buttonSepetOnayla.visibility = View.GONE
                binding.textViewSepetBos.visibility = View.VISIBLE
            } else {
                Log.d("SepetFragment", "Sepet dolu! RecyclerView ve buton görünüyor, TextView gizleniyor. Ürün sayısı: ${liste.size}")
                sepetAdapter.updateList(liste)
                binding.sepetRv.visibility = View.VISIBLE
                binding.buttonSepetOnayla.visibility = View.VISIBLE
                binding.textViewSepetBos.visibility = View.GONE
            }
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