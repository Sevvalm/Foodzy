package com.example.foodzy.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope // viewModelScope kullanmak daha güvenlidir
import com.example.foodzy.data.entity.Sepet
import com.example.foodzy.data.repo.SepetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SepetViewModel @Inject constructor(var sepetRepository: SepetRepository) : ViewModel() {
    var sepetListesi = MutableLiveData<List<Sepet>>(emptyList())

    init {
        sepetiYukle()
        Log.e("SepetViewModel", "ViewModel başlatıldı ve sepetiYukle çağrıldı.")
    }

    fun sepetiYukle() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val liste = sepetRepository.sepetiYukle()
                Log.e("SepetApi", "sepetiYukle() - Gelen liste: $liste")

                sepetListesi.postValue(liste)

                if (liste.isNullOrEmpty()) {
                    Log.e("SepetViewModel", "sepetiYukle() - Sepet boş geldi.")
                } else {
                    Log.e("SepetViewModel", "sepetiYukle() - Sepette ${liste.size} ürün var.")
                }

            } catch (e: Exception) {
                Log.e("SepetApi", "sepetiYukle() - Hata: ${e.localizedMessage}", e)
                sepetListesi.postValue(emptyList())
            }
        }
    }
    fun sepettenSil(sepet_yemek_id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                sepetRepository.sepettenSil(sepet_yemek_id)
                Log.d("SepetViewModel", "sepettenSil() - Sepet yemek ID $sepet_yemek_id silindi.")
                sepetiYukle()
            } catch (e: Exception) {
                Log.e("SepetApi", "sepettenSil() - Hata: ${e.localizedMessage}", e)
            }
        }
    }
}