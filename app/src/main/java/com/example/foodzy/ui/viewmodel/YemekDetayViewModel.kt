package com.example.foodzy.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.foodzy.data.repo.SepetRepository
import com.example.foodzy.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YemekDetayViewModel @Inject constructor(var sepetRepository: SepetRepository) : ViewModel() {

    fun sepeteEkle(yemek_adi : String, yemek_resim_adi : String, yemek_fiyat : Int, yemek_siparis_adet : Int){
        CoroutineScope(Dispatchers.Main).launch {
            sepetRepository.sepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet)
        }
    }
}