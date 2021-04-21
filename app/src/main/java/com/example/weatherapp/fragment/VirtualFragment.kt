package com.example.weatherapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapp.MainActivity
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentHomeBinding
import com.example.weatherapp.databinding.FragmentVirtualBinding
import com.example.weatherapp.model.Main

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [VirtualFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VirtualFragment : Fragment() {
    private lateinit var binding : FragmentVirtualBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVirtualBinding.inflate(inflater)

        binding.next.setOnClickListener {
            val homeFragment = HomeFragment()
            (activity as MainActivity).loadFragment(homeFragment)
        }

        if((activity as MainActivity).permissions == 1){
            binding.next.visibility = View.GONE
        }
        else{
            binding.next.visibility = View.VISIBLE
        }

        return binding.root
    }
}