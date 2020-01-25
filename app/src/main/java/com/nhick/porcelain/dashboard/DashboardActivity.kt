package com.nhick.porcelain.dashboard

import android.graphics.drawable.ColorDrawable
import android.location.Address
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nhick.porcelain.R
import com.nhick.porcelain.Utility
import com.nhick.porcelain.dashboard.fragment.EmptyFragment
import com.nhick.porcelain.dashboard.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() , IDashboardCallback , BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var fragmentChange = false
    private var country = "Philippines"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        setSupportActionBar(app_bar)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        onClickedBlankFragment("Home",HomeFragment())

        bottom_nav.setOnNavigationItemSelectedListener(this)

    }

    override fun onResume() {
        super.onResume()
        if (Utility.createAppPermissions(this,Utility.permissions)) {
            startLocationUpdate()
        }else{
            ActivityCompat.requestPermissions(this, Utility.permissions,1)
        }
    }

    override fun onPause() {
        super.onPause()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1){
            Utility.createAppPermissions(this,permissions)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_app,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_notification -> onClickedBlankFragment("Notification",EmptyFragment())
            R.id.action_message -> onClickedBlankFragment("Message",EmptyFragment())
        }

        return super.onOptionsItemSelected(item)
    }


    private fun startLocationUpdate(){
        fusedLocationClient.requestLocationUpdates(Utility.locationRequest(),
            locationCallback, Looper.getMainLooper())
    }

    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(p0: LocationResult?) {
            super.onLocationResult(p0)
            //update ui
          val addresses = Utility.getAddress(p0!!.lastLocation,this@DashboardActivity)

            if (addresses.isNotEmpty() && !fragmentChange){
                app_bar.title = addresses[0].countryName
                country = addresses[0].countryName
            }else if (addresses.isEmpty() && !fragmentChange){
                app_bar.title = "No Location"
                country = addresses[0].countryName
            }


        }
    }

    override fun onClickedBlankFragment(title: String,fragment: Fragment) {
       supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .setCustomAnimations(R.anim.animation_enter,R.anim.animation_exit,R.anim.animation_enter,R.anim.animation_exit)
            .addToBackStack(null)
            .commit()

        if (fragment is EmptyFragment){
            fragmentChange = true
            supportActionBar?.title = title
            supportActionBar?.setIcon(ColorDrawable(resources.getColor(android.R.color.transparent)))
        }else{
            fragmentChange = false
            supportActionBar?.title = country
            supportActionBar?.setIcon(R.mipmap.location)
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_home -> onClickedBlankFragment("Home",HomeFragment())
        }

        return true
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0){
            onClickedBlankFragment("Home",HomeFragment())
        }
    }

}