package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.CardDetail
import com.example.weatherapp.R

class DetailAdapter(private val listCard: ArrayList<CardDetail>) : RecyclerView.Adapter<DetailAdapter.DetailHolder>(){
    class DetailHolder(view: View) : RecyclerView.ViewHolder(view) {
        val weatherIcon: ImageView = view.findViewById(R.id.weatherIcon)
        val dayInfo: TextView = view.findViewById(R.id.dayInfo)
        val dayTempo: TextView = view.findViewById(R.id.tempMax)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_detail, parent, false)
        return DetailHolder(view)
    }

    override fun getItemCount(): Int {
        return listCard.size
    }

    override fun onBindViewHolder(holder: DetailHolder, position: Int) {
        val currentCard = listCard[position]

//        holder.weatherIcon.setImageResource(currentCard.weatherIcon)
        holder.dayInfo.text = currentCard.dayInfo
        holder.dayTempo.text = currentCard.dayTempo.toString()
    }
}