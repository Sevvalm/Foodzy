package com.example.foodzy.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.foodzy.data.entity.Yemekler
import com.example.foodzy.databinding.CardFoodTasarimBinding
import com.example.foodzy.ui.fragment.AnasayfaFragmentDirections

class YemeklerAdapter (var mContext: Context, var yemeklerListesi: List<Yemekler>) : RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu>(){

    inner class CardTasarimTutucu(var tasarim: CardFoodTasarimBinding) : RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = CardFoodTasarimBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val yemek = yemeklerListesi.get(position)
        val y = holder.tasarim

        y.textViewYemekAdi.text = yemek.yemek_adi
        y.textViewFiyat.text = yemek.yemek_fiyat.toString()

        y.cardViewSatir.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.yemekDetayGecisA(yemek=yemek)
            Navigation.findNavController(it).navigate(gecis)
        }
        y.buttonSepet.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.yemekDetayGecisA(yemek=yemek)
            Navigation.findNavController(it).navigate(gecis)
        }

    }

    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }
}