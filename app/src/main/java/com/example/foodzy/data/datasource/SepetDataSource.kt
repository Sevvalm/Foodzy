package com.example.foodzy.data.datasource

import com.example.foodzy.data.entity.Sepet
import com.example.foodzy.data.entity.Yemekler
import com.example.foodzy.retrofit.SepetDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.collections.List

class SepetDataSource(var sepetDao: SepetDao) {
    val kullanici_adi ="SevvalM"

    suspend fun sepetiYukle(): List<Sepet> = withContext(Dispatchers.IO) {
            return@withContext sepetDao.sepettekiYemekleriYukle(kullanici_adi).sepetYemekler
    }

    suspend fun sepeteEkle(yemek_adi : String,
                           yemek_resim_adi : String,
                           yemek_fiyat : Int,
                           yemek_siparis_adet : Int){
        sepetDao.sepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
    }

    suspend fun sepettenSil(sepet_yemek_id: Int) {
        sepetDao.sepettenYemekSil(sepet_yemek_id,kullanici_adi)
    }
}