package com.example.foodzy.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Yemekler(@SerializedName("yemek_id") val yemek_id: String,
                    @SerializedName("yemek_adi") val yemek_adi: String,
                    @SerializedName("yemek_resim_adi") val yemek_resim_adi: String,
                    @SerializedName("yemek_fiyat") val yemek_fiyat: String) : Serializable {
}