package com.example.foodzy.data.repo

import com.example.foodzy.data.datasource.SepetDataSource
import com.example.foodzy.data.entity.Sepet

class SepetRepository(var sepetDataSource: SepetDataSource) {

    suspend fun sepetiYukle() : List<Sepet>{
       return sepetDataSource.sepetiYukle()
    }

    suspend fun sepeteEkle(yemek_adi : String,
                           yemek_resim_adi : String,
                           yemek_fiyat : Int,
                           yemek_siparis_adet : Int){
        sepetDataSource.sepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet)
    }

    suspend fun sepettenSil(sepet_yemek_id: Int){
        sepetDataSource.sepettenSil(sepet_yemek_id)
    }

}