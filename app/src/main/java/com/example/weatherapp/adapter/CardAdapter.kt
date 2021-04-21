package com.example.weatherapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.DayWeather
import com.example.weatherapp.R
import com.squareup.picasso.Picasso

class CardAdapter(private val listDay: ArrayList<DayWeather>) :
    RecyclerView.Adapter<CardAdapter.CardHolder>() {
    class CardHolder(view: View) : RecyclerView.ViewHolder(view) {
        val day: TextView = view.findViewById(R.id.day)
        val hour: TextView = view.findViewById(R.id.hour)
        val tempo: TextView = view.findViewById(R.id.tempo)
        val icon: ImageView = view.findViewById(R.id.iconInCard)
    }

    //    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
//        context = parent.context
        return CardHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listDay.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        val currentItem = listDay[position]

        holder.day.text = currentItem.day
        holder.hour.text = currentItem.hour
        holder.tempo.text = currentItem.tempo.toString() + "°C"
        Picasso.with(holder.day.context).load(currentItem.icon).resize(100, 100).into(holder.icon)
    }
}