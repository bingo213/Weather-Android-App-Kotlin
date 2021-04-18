package com.example.weatherapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.DayWeather
import com.example.weatherapp.R
import com.example.weatherapp.adapter.CardAdapter
import com.example.weatherapp.databinding.FragmentHomeBinding
import com.example.weatherapp.viewmodel.HomeViewModel
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class HomeFragment : Fragment() {
    private lateinit var dayAdapter: CardAdapter
    private lateinit var dayRecyclerView: RecyclerView
    private var listDayWeather: ArrayList<DayWeather> = ArrayList()

    private lateinit var binding: FragmentHomeBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)

        dayAdapter = CardAdapter(listDayWeather)
        dayRecyclerView = binding.bottom
        dayRecyclerView.apply {
            setHasFixedSize(true)
            adapter = dayAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }

        binding.seeMore.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
        }

        val viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.weather.observe(viewLifecycleOwner, Observer { weather ->
            binding.tempo.text = weather.main?.temp?.let { convertKelvinToCelsius(it) }
//                (weather.main?.temp?.minus(273.15))?.let { Math.round(it).toString() } + "°C"
            binding.description.text = weather.weather[0].main
            binding.dateTime.text = DateFormat.getDateInstance(DateFormat.FULL, Locale.US)
                .format(Calendar.getInstance().time).toString()
            binding.address.text = weather.name

            binding.humidity.text = weather.main?.humidity?.let { Math.round(it).toString() } + "%"
            binding.realFeel.text = weather.main?.feels_like?.let { convertKelvinToCelsius(it) }
            binding.wind.text = weather.wind?.speed?.times(3.6)?.let { it.roundToInt().toString() } + " km/h"
            binding.cloudness.text = weather.clouds?.all?.let { it.roundToInt().toString() } + "%"
        })

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        for (i: Int in 1..7) {
            listDayWeather.add(DayWeather("Thus", 24))
        }
    }

    private fun convertKelvinToCelsius(temp: Float): String {
        return (temp.minus(273.15)).roundToInt().toString() + "°C"
    }

}