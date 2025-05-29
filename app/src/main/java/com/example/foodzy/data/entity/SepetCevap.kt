package com.example.foodzy.data.entity

import com.google.gson.annotations.SerializedName

class SepetCevap(@SerializedName("sepet_yemekler") var sepetYemekler: List<Sepet>,
                 @SerializedName("success") var success: Int) {
}