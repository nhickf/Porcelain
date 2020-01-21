package com.nhick.porcelain.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nhick.porcelain.R
import com.nhick.porcelain.dashboard.fragment.HomeFragment

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,HomeFragment())
            .commit()
    }




}