package com.example.weatherapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.*
import com.example.weatherapp.adapter.CardAdapter
import com.example.weatherapp.databinding.FragmentHomeBinding
import com.example.weatherapp.viewmodel.HomeViewModel
import com.example.weatherapp.viewmodel.DetailViewModel
import com.squareup.picasso.Picasso
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class HomeFragment : Fragment() {
    private lateinit var dayAdapter: CardAdapter
    private lateinit var dayRecyclerView: RecyclerView
    private var listDayWeather : ArrayList<DayWeather> = ArrayList()

    private lateinit var binding: FragmentHomeBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("HomeFragment", "createView")
        binding = FragmentHomeBinding.inflate(inflater)

        binding.seeMore.setOnClickListener { view: View ->
            val detailFragment = DetailFragment()
            (activity as MainActivity).loadFragment(detailFragment)
        }

        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        homeViewModel.weather.observe(viewLifecycleOwner, Observer { weather ->
            binding.tempo.text = weather.main?.temp?.let { convertKelvinToCelsius(it) }

            binding.mainWeather.text = weather.weather[0].main
            binding.description.text = weather.weather[0].description
            binding.dateTime.text = DateFormat.getDateInstance(DateFormat.FULL, Locale.US)
                .format(Calendar.getInstance().time).toString()
            binding.address.text = weather.name

            binding.humidity.text = weather.main?.humidity?.roundToInt()?.toString() + "%"
            binding.progressHumidity.progress = weather.main?.humidity?.roundToInt()!!

            binding.realFeel.text = weather.main?.feels_like?.let { convertKelvinToCelsius(it) }
            binding.progressRealFeel.progress =
                weather.main?.feels_like?.minus(KELVIN_TO_CELSIUS)?.roundToInt()!!

            binding.wind.text =
                weather.wind?.speed?.times(METER_PER_SECOND_TO_KILOMETER_PER_HOUR)?.roundToInt()
                    ?.toString() + " km/h"
            binding.progressWind.progress = weather.wind?.speed?.times(
                METER_PER_SECOND_TO_KILOMETER_PER_HOUR
            )?.roundToInt()!!

            binding.cloudness.text = weather.clouds?.all?.roundToInt()?.toString() + "%"
            binding.progressCloudness.progress = weather.clouds?.all?.roundToInt()!!

            val iconUrl = BASE_URL + "/img/w/" + weather.weather[0].icon + ".png"
            Picasso.with(activity).load(iconUrl).resize(200, 200).into(binding.icon)
        })

        dayRecyclerView = binding.bottom
        homeViewModel.listDayWeather.observe(viewLifecycleOwner, Observer {
            dayAdapter = CardAdapter(it)
            dayRecyclerView.adapter = dayAdapter
        })

        dayRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }

        return binding.root
    }

    private fun convertKelvinToCelsius(temp: Float): String {
        return (temp.minus(KELVIN_TO_CELSIUS)).roundToInt().toString() + "°C"
    }

//    override fun onStop() {
//        super.onStop()
//        Log.i("HomeFragment", "Stop")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.i("HomeFragment", "Destroy")
//    }

}