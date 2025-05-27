package com.example.foodzy.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodzy.R
import com.example.foodzy.databinding.FragmentYemekDetayBinding


class YemekDetayFragment : Fragment() {
private lateinit var binding: FragmentYemekDetayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentYemekDetayBinding.inflate(inflater,container,false)

        return binding.root
    }

}