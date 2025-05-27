package com.example.foodzy.ui.viewmodel

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

    //sil ekle

    fun sepetiYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            sepetListesi.value = sepetRepository.sepetiYukle()
        }
    }

}