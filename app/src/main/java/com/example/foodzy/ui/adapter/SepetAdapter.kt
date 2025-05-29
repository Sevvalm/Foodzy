package com.example.foodzy.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodzy.data.entity.Sepet
import com.example.foodzy.data.entity.Yemekler
import com.example.foodzy.databinding.CardSepetTasarimBinding
import com.example.foodzy.ui.viewmodel.SepetViewModel
import com.google.android.material.snackbar.Snackbar

class SepetAdapter (var mContext: Context, var sepetListesi : List<Sepet>, var viewModel : SepetViewModel) : RecyclerView.Adapter<SepetAdapter.CardSepetTasarimTutucu>(){

    inner class CardSepetTasarimTutucu(var tasarim : CardSepetTasarimBinding) : RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardSepetTasarimTutucu {
        val tasarim = CardSepetTasarimBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return CardSepetTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardSepetTasarimTutucu, position: Int) {
        val sepet = sepetListesi.get(position)
        val s = holder.tasarim

        var odencekUcret = sepet.yemek_siparis_adet.toInt() * sepet.yemek_fiyat.toInt()

        s.textViewYemekAdiSepet.text = sepet.yemek_adi
        s.textViewYemekAdetSepet.text = sepet.yemek_siparis_adet.toString()
        s.textViewFiyatSepet.text = "${odencekUcret} ₺"

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${sepet.yemek_resim_adi}"
        Glide.with(mContext).load(url).override(300,300).centerCrop().into(s.imageView2)

        s.buttonSepetSil.setOnClickListener {
            Snackbar.make(it,"${sepet.yemek_adi} sepetten silinsin mi?", Snackbar.LENGTH_SHORT)
                .setAction("EVET") {
                viewModel.sepettenSil(sepet.sepet_yemek_id)
            }.show()
        }
        viewModel.sepetiYukle()


    }
    override fun getItemCount(): Int = sepetListesi.size

    fun updateList(newList: List<Sepet>) {
        sepetListesi = newList
        notifyDataSetChanged()
    }
}