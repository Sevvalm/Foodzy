package com.example.foodzy.retrofit

import com.example.foodzy.data.datasource.SepetDataSource
import com.example.foodzy.data.entity.CRUDCevap
import com.example.foodzy.data.entity.SepetCevap
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SepetDao {

    @POST("sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun sepettekiYemekleriYukle(@Field("kullanici_adi") kullaniciAdi: String) : SepetCevap

    @POST("sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun sepeteEkle(@Field("yemek_adi") yemek_adi:String,
                           @Field("yemek_resim_adi") yemek_resim_adi:String,
                           @Field("yemek_fiyat") yemek_fiyat:Int,
                           @Field("yemek_siparis_adet") yemek_siparis_adet:Int,
                           @Field("kullanici_adi") kullanici_adi:String) : CRUDCevap

    @POST("sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun sepettenYemekSil(@Field("sepet_yemek_id") sepet_yemek_id:Int,
                                 @Field("kullanici_adi") kullanici_adi:String) : CRUDCevap
}