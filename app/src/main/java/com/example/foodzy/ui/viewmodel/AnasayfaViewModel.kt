package com.example.foodzy.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodzy.data.entity.Yemekler
import com.example.foodzy.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnasayfaViewModel @Inject constructor(var yemeklerRepository: YemeklerRepository) : ViewModel() {
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()
    private var tumYemeklerListesi = listOf<Yemekler>()

    init {
        yemekleriYukle()
    }

    fun yemekleriYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val liste = yemeklerRepository.yemekleriYukle()
                Log.e("YemeklerApi", "Gelen liste: $liste")
                tumYemeklerListesi = liste
                yemeklerListesi.value = liste
            } catch (e: Exception) {
                Log.e("YemeklerApi", "Hata: ${e.localizedMessage}")
            }
        }
    }

    fun ara(aramaKelimesi: String){
        val filtreliListe = if(aramaKelimesi.isBlank()){
            tumYemeklerListesi
        }
        else{
            tumYemeklerListesi.filter {
                it.yemek_adi.contains(aramaKelimesi,ignoreCase = true)
            }
        }
        yemeklerListesi.value = filtreliListe
    }
}