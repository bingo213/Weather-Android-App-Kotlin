package com.example.weatherapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.CardDetail
import com.example.weatherapp.R
import com.example.weatherapp.R.id.homeFragment
import com.example.weatherapp.adapter.DetailAdapter
import com.example.weatherapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private lateinit var detailAdapter: DetailAdapter
    private lateinit var detailRecyclerView: RecyclerView
    private var lisrCardDetail: ArrayList<CardDetail> = ArrayList()

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)

        detailAdapter = DetailAdapter(lisrCardDetail)
        detailRecyclerView = binding.listCard
        detailRecyclerView.apply {
            setHasFixedSize(true)
            adapter = detailAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        binding.backBtn.setOnClickListener { view : View ->
//            view.findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
            view.findNavController().navigateUp()
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        for (i : Int in 1..10){
            lisrCardDetail.add(CardDetail("Fri, 19 March 2021", 26))
        }
    }

}