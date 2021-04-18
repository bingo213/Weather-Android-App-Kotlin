package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.DayWeather
import com.example.weatherapp.R

class CardAdapter (private val listDay: ArrayList<DayWeather>) : RecyclerView.Adapter<CardAdapter.CardHolder>(){
    class CardHolder (view: View): RecyclerView.ViewHolder(view){
        val day: TextView = view.findViewById(R.id.day)
//        val ordinary: TextView = view.findViewById(R.id.ordinary)
        val tempo: TextView = view.findViewById(R.id.tempo)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        when (viewType){
            0 -> return CardHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_item_first, parent, false))
            else -> {
                return CardHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false))
            }
        }
    }

    override fun getItemCount(): Int {
        return listDay.size
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        val currentItem = listDay[position]

        holder.day.text = currentItem.day
//        holder.ordinary.text = currentItem.ordinary.toString()
        holder.tempo.text = currentItem.tempo.toString()
    }
}