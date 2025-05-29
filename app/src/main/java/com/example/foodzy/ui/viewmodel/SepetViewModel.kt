package com.example.foodzy.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodzy.data.entity.Sepet
import com.example.foodzy.data.entity.Yemekler
import com.example.foodzy.data.repo.SepetRepository
import com.example.foodzy.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SepetViewModel @Inject constructor(var sepetRepository: SepetRepository) : ViewModel() {
    var sepetListesi = MutableLiveData<List<Sepet>>()

    init {
        sepetiYukle()
    }
    fun sepetiYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val liste = sepetRepository.sepetiYukle()
                Log.e("SepetApi", "Gelen liste: $liste")
                sepetListesi.value = liste
            } catch (e: Exception) {
                Log.e("SepetApi", "Hata: ${e.localizedMessage}")
            }
        //sepetListesi.value = sepetRepository.sepetiYukle()
        }
    }

    fun sepettenSil(sepet_yemek_id : Int){
        CoroutineScope(Dispatchers.IO).launch {
            sepetRepository.sepettenSil(sepet_yemek_id)
        }
    }

}