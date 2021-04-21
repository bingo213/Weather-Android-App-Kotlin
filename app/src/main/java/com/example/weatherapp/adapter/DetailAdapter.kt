package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.CardDetail
import com.example.weatherapp.R
import com.squareup.picasso.Picasso

class DetailAdapter(private val listCard: ArrayList<CardDetail>) : RecyclerView.Adapter<DetailAdapter.DetailHolder>(){
    class DetailHolder(view: View) : RecyclerView.ViewHolder(view) {
        val weatherIcon: ImageView = view.findViewById(R.id.weatherIcon)
        val dayInfo: TextView = view.findViewById(R.id.dayInfo)
        val tempMax: TextView = view.findViewById(R.id.tempMax)
        val tempMin: TextView = view.findViewById(R.id.tempMin)
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
        holder.dayInfo.text = currentCard.dateTime
        holder.tempMax.text = currentCard.tempMax.toString()
        holder.tempMin.text = currentCard.tempMin.toString()
        Picasso.with(holder.dayInfo.context).load(currentCard.icon).resize(100, 100).into(holder.weatherIcon)
    }
}