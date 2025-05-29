package com.example.foodzy.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodzy.data.entity.Yemekler
import com.example.foodzy.databinding.CardFoodTasarimBinding
import com.example.foodzy.ui.fragment.AnasayfaFragmentDirections
import com.example.foodzy.ui.viewmodel.AnasayfaViewModel
import com.example.foodzy.utils.gecisYap

class YemeklerAdapter (var mContext: Context, var yemeklerListesi: List<Yemekler> , var viewModel: AnasayfaViewModel) : RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu>(){

    inner class CardTasarimTutucu(var tasarim: CardFoodTasarimBinding) : RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = CardFoodTasarimBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val yemek = yemeklerListesi.get(position)
        val y = holder.tasarim

        y.textViewYemekAdi.text = yemek.yemek_adi
        y.textViewFiyat.text = "${yemek.yemek_fiyat} ₺"

        y.cardViewSatir.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.yemekDetayGecisA(yemek=yemek)
            Navigation.gecisYap(it,gecis)
        }
        y.buttonSepet.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.yemekDetayGecisA(yemek=yemek)
            Navigation.gecisYap(it,gecis)
        }

        //resimleri al
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Glide.with(mContext).load(url).override(300,300).centerCrop().into(y.imageView)

    }
    override fun getItemCount(): Int = yemeklerListesi.size

    fun updateList(newList: List<Yemekler>) {
        yemeklerListesi = newList
        notifyDataSetChanged()
    }
}