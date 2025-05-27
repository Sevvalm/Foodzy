package com.example.foodzy.data.repo

import com.example.foodzy.data.datasource.YemeklerDataSource
import com.example.foodzy.data.entity.Yemekler

class YemeklerRepository(var yemeklerDataSource : YemeklerDataSource) {

    suspend fun yemekleriYukle() : List<Yemekler> = yemeklerDataSource.yemekleriYukle()
}