package com.example.weatherapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.BASE_URL
import com.example.weatherapp.KELVIN_TO_CELSIUS
import com.example.weatherapp.METER_PER_SECOND_TO_KILOMETER_PER_HOUR
import com.example.weatherapp.MainActivity
import com.example.weatherapp.R.id.homeFragment
import com.example.weatherapp.adapter.DetailAdapter
import com.example.weatherapp.databinding.FragmentDetailBinding
import com.example.weatherapp.viewmodel.DetailViewModel
import com.squareup.picasso.Picasso
import java.text.DateFormat
import java.util.*
import kotlin.math.roundToInt

class DetailFragment : Fragment() {
    private lateinit var detailAdapter: DetailAdapter
    private lateinit var detailRecyclerView: RecyclerView

    private lateinit var binding: FragmentDetailBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)
        val detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        binding.backBtn.setOnClickListener { view : View ->
            (activity as MainActivity).removeFragment(this)
        }

        detailRecyclerView = binding.listCard
        detailViewModel.listDailyWeather.observe(viewLifecycleOwner, Observer {cardDetail ->
            detailAdapter = DetailAdapter(cardDetail)
            detailRecyclerView.adapter = detailAdapter
        })
        detailRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
        }

        detailViewModel.weather.observe(viewLifecycleOwner, Observer {weather ->
            binding.cloudness.text = weather.clouds?.all?.roundToInt()?.toString() + "%"
            binding.temp.text = weather.main?.temp?.minus(KELVIN_TO_CELSIUS)?.roundToInt().toString() + "°C"

            binding.date.text = DateFormat.getDateInstance(DateFormat.FULL, Locale.US)
                .format(Calendar.getInstance().time).toString()

            binding.humidity.text = weather.main?.humidity?.roundToInt()?.toString() + "%"

            binding.realFeel.text = weather.main?.feels_like?.minus(KELVIN_TO_CELSIUS)?.roundToInt().toString() + "°C"

            binding.wind.text =
                weather.wind?.speed?.times(METER_PER_SECOND_TO_KILOMETER_PER_HOUR)?.roundToInt()
                    ?.toString() + " km/h"

            binding.main.text = weather.weather[0].main
            val iconUrl = BASE_URL + "/img/w/" + weather.weather[0].icon + ".png"
            Picasso.with(activity).load(iconUrl).resize(200, 200).into(binding.icon)
        })

        return binding.root
    }

}