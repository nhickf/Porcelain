package com.nhick.porcelain

import android.Manifest.permission.*
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationRequest
import java.lang.Exception

class Utility {


    companion object{

        val permissions = arrayOf(ACCESS_COARSE_LOCATION,ACCESS_FINE_LOCATION)
        val NUMBER_CODE_KEY = "CODE_KEY"


        fun locationRequest() : LocationRequest{
            return LocationRequest.create().apply {
                interval = 10000
                fastestInterval = 5000
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            }
        }

        fun createAppPermissions(context:Context , permissions : Array<String>) : Boolean{

            var isAllowed = false

            for (permission in permissions){
               if (ActivityCompat.checkSelfPermission(context,permission) == PackageManager.PERMISSION_GRANTED){
                   isAllowed = true
               }
            }

            return isAllowed
        }

        fun getAddress(location : Location,context: Context) : ArrayList<Address>{
            var addresses = ArrayList<Address>()
            val geocoder = Geocoder(context)


            try {
                addresses = geocoder.getFromLocation(location.latitude,location.longitude,1) as ArrayList<Address>
            }catch (e:Exception){
                Log.e("LocationGeocoder","Service not Available")
            }



            return addresses

        }



    }


}