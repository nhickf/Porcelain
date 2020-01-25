package com.nhick.porcelain.dashboard

import androidx.fragment.app.Fragment
import com.nhick.porcelain.repository.Banner

interface IDashboardCallback {

    fun onClickedBlankFragment(title:String , fragment: Fragment)

    interface onBannerListener{

        fun onMoreClicked(banner: Banner)

    }

}