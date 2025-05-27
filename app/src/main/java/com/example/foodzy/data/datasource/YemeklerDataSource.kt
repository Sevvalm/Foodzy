package com.example.foodzy.data.datasource

import com.example.foodzy.data.entity.Yemekler
import com.example.foodzy.retrofit.YemeklerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YemeklerDataSource(var yemeklerDao : YemeklerDao) {

   suspend fun yemekleriYukle(): List<Yemekler> = withContext(Dispatchers.IO) {
        return@withContext yemeklerDao.yemekleriYukle().sepetYemekler
    }
}