package com.example.weatherapp

import android.Manifest
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.databinding.FragmentHomeBinding
import java.util.*

import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //Declaring the needed Variables
//    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
//    lateinit var locationRequest: LocationRequest
//    val PERMISSION_ID = 21

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
//        val btn = binding.getpos
//            btn.setOnClickListener {
//            Log.d("Debug:",checkPermission().toString())
//            Log.d("Debug:",isLocationEnabled().toString())
//            RequestPermission()
//            getLastLocation()
//        }

    }


//    fun getLastLocation(){
//        if(checkPermission()){
//            if(isLocationEnabled()){
//                fusedLocationProviderClient.lastLocation.addOnCompleteListener {task->
//                    var location:Location? = task.result
//                    if(location == null){
////                        NewLocationData()
//                        Log.d("Debug", "location null")
//                    }else{
//                        Log.d("Debug:" ,"Your Location: Longtitude: "+ location.longitude + "Latitude: "+ location.latitude)
//                    }
//                }
//            }else{
//                Toast.makeText(this,"Please Turn on Your device Location",Toast.LENGTH_SHORT).show()
//            }
//        }else{
//            RequestPermission()
//        }
//    }
//
//
//    fun NewLocationData(){
//        locationRequest =  LocationRequest()
//        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//        locationRequest.interval = 0
//        locationRequest.fastestInterval = 0
//        locationRequest.numUpdates = 1
//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
//        checkPermission()
//        fusedLocationProviderClient!!.requestLocationUpdates(
//                locationRequest,locationCallback,Looper.myLooper()
//        )
//    }
//
//
//    private val locationCallback = object : LocationCallback(){
//        override fun onLocationResult(locationResult: LocationResult) {
//            var lastLocation: Location = locationResult.lastLocation
//            Log.d("Debug:","your last last location: "+ lastLocation.longitude.toString())
//        }
//    }
//
//    private fun checkPermission():Boolean{
//        //this function will return a boolean
//        //true: if we have permission
//        //false if not
//        if(
//                ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
//                ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
//        ){
//            return true
//        }
//
//        return false
//
//    }
//
//    fun RequestPermission(){
//        //this function will allows us to tell the user to requesut the necessary permsiion if they are not garented
//        ActivityCompat.requestPermissions(
//                this,
//                arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest.permission.ACCESS_FINE_LOCATION),
//                PERMISSION_ID
//        )
//    }
//
//    fun isLocationEnabled():Boolean{
//        //this function will return to us the state of the location service
//        //if the gps or the network provider is enabled then it will return true otherwise it will return false
//        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
//    }
//
//
//    override fun onRequestPermissionsResult(
//            requestCode: Int,
//            permissions: Array<out String>,
//            grantResults: IntArray
//    ) {
//        if(requestCode == PERMISSION_ID){
//            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                Log.d("Debug:","You have the Permission")
//            }
//        }
//    }
//
//    private fun getCityName(lat: Double,long: Double):String{
//        var cityName:String = ""
//        var countryName = ""
//        var geoCoder = Geocoder(this, Locale.getDefault())
//        var Adress = geoCoder.getFromLocation(lat,long,3)
//
//        cityName = Adress.get(0).locality
//        countryName = Adress.get(0).countryName
//        Log.d("Debug:","Your City: " + cityName + " ; your Country " + countryName)
//        return cityName
//    }

}