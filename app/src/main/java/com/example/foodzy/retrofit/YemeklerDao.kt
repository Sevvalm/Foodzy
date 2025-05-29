package com.example.foodzy.retrofit

import androidx.annotation.GuardedBy
import com.example.foodzy.data.entity.YemeklerCevap
import retrofit2.http.GET
import retrofit2.http.POST

interface YemeklerDao {

   @GET("tumYemekleriGetir.php")
   suspend fun yemekleriYukle() : YemeklerCevap

   @GET("resimler/")
   suspend fun yemekResimleriGetir()
}